package com.logic.app.roomdatabase

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.viewbinding.ViewBinding
import com.logic.app.roomdatabase.adapter.OnItemClickListener
import com.logic.app.roomdatabase.adapter.StudentAdapter
import com.logic.app.roomdatabase.adapter.UserAdapter
import com.logic.app.roomdatabase.database.*
import com.logic.app.roomdatabase.databinding.ActivityMainBinding
import com.logic.app.roomdatabase.databinding.DialogUserBinding
import com.logic.app.roomdatabase.utils.log
import com.logic.app.roomdatabase.utils.toast


class MainActivity : AppCompatActivity(), StudentAdapter.OnItemClickListener, OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: MyDatabase

    private lateinit var userDao: UserDAO
    private lateinit var studentDao: StudentDAO

    private val addToStudent by lazy { binding.addTop }
    private val addToUser by lazy { binding.addBottom }

    private val removeStudent by lazy { binding.removeTop }
    private val removeUser by lazy { binding.removeBottom }

    private lateinit var recStudent: RecyclerView
    private lateinit var recUser: RecyclerView
    private lateinit var adapterStudent: StudentAdapter
    private lateinit var adapterUser: UserAdapter

    private lateinit var listStudent: MutableList<Student>
    private lateinit var listUser: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbs()
        initializeView()

        addToStudent.setOnClickListener {


            val items = setDialog(DialogUserBinding.inflate(layoutInflater))
            val bind: DialogUserBinding = items[0] as DialogUserBinding
            val alert: AlertDialog = items[1] as AlertDialog

            bind.edtAddress.isClickable = false
            bind.edtAddress.visibility = View.GONE
            bind.textView3.visibility = View.GONE

            bind.edtId.visibility = View.GONE
            bind.textView.visibility = View.GONE

            bind.edtCountry.isClickable = false
            bind.edtCountry.visibility = View.GONE
            bind.textView2.visibility = View.GONE

            bind.save.setOnClickListener {


                if (bind.edtId.text.isNotEmpty() && bind.edtName.text.isNotEmpty() && bind.edtPhone.text.isNotEmpty()) {

                    val s = Student()
                    s.name = bind.edtName.text.toString()
                    s.phone = bind.edtPhone.text.toString()
                    log("size student  ${listStudent.size}")
                    studentDao.insertStudent(s)
                    listStudent.add(listStudent.size, studentDao.allStudent.last())
                    adapterStudent.notifyItemInserted(listStudent.size)
                    recStudent.smoothScrollToPosition(listStudent.size)

                    alert.dismiss()
                } else toast("the value is Empty...")


            }
        }
        addToUser.setOnClickListener {
            val items = setDialog(DialogUserBinding.inflate(layoutInflater))
            val bind: DialogUserBinding = items[0] as DialogUserBinding
            val alert: AlertDialog = items[1] as AlertDialog

            bind.edtPhone.isClickable = false
            bind.edtPhone.visibility = View.GONE
            bind.textView5.visibility = View.GONE

            bind.edtId.visibility = View.GONE
            bind.textView.visibility = View.GONE

            bind.edtName.isClickable = false
            bind.edtName.visibility = View.GONE
            bind.textView4.visibility = View.GONE

            bind.save.setOnClickListener {

                if (bind.edtId.text.isNotEmpty() && bind.edtAddress.text.isNotEmpty() && bind.edtCountry.text.isNotEmpty()) {

                    val u = User()
                    u.address = bind.edtAddress.text.toString()
                    u.country = bind.edtCountry.text.toString()

                    userDao.insertUser(u)

                    listUser.add(listUser.size, userDao.allUser.last())
                    listUser.forEach {

                        log(
                            "  ***\n\nuserCountry ${it.country.toString()} \n " +
                                    "userAddress ${it.address.toString()} \n" +
                                    " userId ${it.id} \n" +
                                    "*************"
                        )
                    }
                    log("size user ${listUser.size}")
                    adapterUser.notifyItemInserted(listUser.size)
//                    recUser.scrollToPosition(listUser.size)
//                    recUser.verticalScrollbarPosition=(listUser.size-1)
                    recUser.smoothScrollToPosition(listUser.size)
                    alert.dismiss()
                } else toast("the value is Empty...")


            }
        }


    }

    private fun dbs() {
        db = Room.databaseBuilder(this, MyDatabase::class.java, "mo.db").allowMainThreadQueries()
            .build()

        userDao = db.userDAO()!!
        studentDao = db.studentDAO()!!

    }

    private fun initializeView() {

        recStudent = binding.recStudent
        recUser = binding.recUser


        listStudent = ArrayList()
        listStudent.addAll(studentDao.allStudent)

        recStudent.layoutManager = GridLayoutManager(this, 3)
        adapterStudent = StudentAdapter(listStudent, this)


        recStudent.adapter = adapterStudent
        recStudent.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )

        listUser = ArrayList()
        listUser.addAll(userDao.allUser)
        recUser.layoutManager = GridLayoutManager(this, 2)
        adapterUser = UserAdapter(listUser, this)

        recUser.adapter = adapterUser
        recUser.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )


    }


    private fun setDialog(
        bind: ViewBinding,
        cancelable: Boolean = true,
        gravity: Int = Gravity.BOTTOM
    ): Array<Any> {

        /*
            val items = setDialog(DialogUserBinding.inflate(layoutInflater), this)
            val bind: DialogUserBinding = items[0] as DialogUserBinding
            val alert: AlertDialog = items[1] as AlertDialog

         */

        val alert = AlertDialog.Builder(bind.root.context)
        alert.setView(bind.root)
        alert.context.setTheme(R.style.dialogAnim)
        val alertDialog = alert.create()
        if (alertDialog.isShowing) {
            alertDialog.dismiss()
        }

        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCanceledOnTouchOutside(cancelable)
        alertDialog.window!!.setGravity(gravity)
        alertDialog.window!!.setWindowAnimations(R.style.dialogAnim)
        alertDialog.show()


        return arrayOf(bind, alertDialog)
    }

    @SuppressLint("SetTextI18n")
    override fun onItemClickUser(user: User, position: Int) {
        log("\n************\n")
        log("\nUserClick  id_${user.id}\n")
        log("UserClick  address_${user.address}\n")
        log("UserClick  country_${user.country}\n")


        val items = setDialog(DialogUserBinding.inflate(layoutInflater))
        val bind: DialogUserBinding = items[0] as DialogUserBinding
        val alert: AlertDialog = items[1] as AlertDialog

        bind.edtPhone.isClickable = false
        bind.edtPhone.visibility = View.GONE
        bind.textView5.visibility = View.GONE


        bind.edtName.isClickable = false
        bind.edtName.visibility = View.GONE
        bind.textView4.visibility = View.GONE


        bind.edtId.setText(user.id.toString())
        bind.edtAddress.setText(user.address)
        bind.edtCountry.setText(user.country)
        bind.save.text = "Update"



        bind.save.setOnClickListener {


            if (bind.edtAddress.text.isNotEmpty() && bind.edtCountry.text.isNotEmpty()) {

                val u = User()
                u.id = user.id
                u.address = bind.edtAddress.text.toString()
                u.country = bind.edtCountry.text.toString()

                userDao.updateUser(u)

                listUser.clear()
                listUser.addAll(userDao.allUser)
//                listUser.forEach {
//
//                    log(
//                        "  ***\n\nuserCountry ${it.country.toString()} \n " +
//                                "userAddress ${it.address.toString()} \n" +
//                                " userId ${it.id} \n" +
//                                "*************"
//                    )
//                }


                log("po $position")
                adapterUser.notifyItemChanged(position)
                recUser.smoothScrollToPosition(position)
                alert.dismiss()
            } else toast("the value is Empty...")


        }


    }

    @SuppressLint("SetTextI18n")
    override fun onItemClickStudent(student: Student, position: Int) {
        log("\n************\n")
        log("\nStudentClick  uid_${student.uid}\n")
        log("StudentClick  name_${student.name}\n")
        log("StudentClick  student_${student.phone}\n")


        val items = setDialog(DialogUserBinding.inflate(layoutInflater))
        val bind: DialogUserBinding = items[0] as DialogUserBinding
        val alert: AlertDialog = items[1] as AlertDialog

        bind.edtAddress.isClickable = false
        bind.edtAddress.visibility = View.GONE
        bind.textView3.visibility = View.GONE

        bind.edtId.visibility = View.GONE
        bind.textView.visibility = View.GONE

        bind.edtCountry.isClickable = false
        bind.edtCountry.visibility = View.GONE
        bind.textView2.visibility = View.GONE


        bind.edtId.setText(student.uid.toString())
        bind.edtName.setText(student.name)
        bind.edtPhone.setText(student.phone)




        bind.save.text = "Update"



        bind.save.setOnClickListener {
            if (bind.edtName.text.isNotEmpty() && bind.edtPhone.text.isNotEmpty()) {
                val s = Student()
                s.uid = student.uid
                s.name = bind.edtName.text.toString()
                s.phone = bind.edtPhone.text.toString()
                studentDao.updateStudent(s)

                listStudent.clear()
                listStudent.addAll(studentDao.allStudent)
                adapterStudent.notifyItemChanged(position)
                recStudent.smoothScrollToPosition(position)

                alert.dismiss()
            } else toast("the value is Empty...")


        }

    }

}