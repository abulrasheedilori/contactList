package com.example.savecontacttodatabase


import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savecontacttodatabase.contact.ContactData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(),
    ContactRecyclerViewAdapter.OnItemClickListen,
    ContactRecyclerViewAdapter.OnLongClickListen  {

    private lateinit var dbref : DatabaseReference
    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var contactList: ArrayList<ContactData>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //population the recyclerview
        contactRecyclerView = findViewById(R.id.recyclerView)
        contactRecyclerView.layoutManager = LinearLayoutManager(this)
        contactRecyclerView.setHasFixedSize(true)

        contactList = arrayListOf()
        getUserDataFromDatabase()

        val adapter = ContactRecyclerViewAdapter(contactList, null,null)
        val contact = ContactData()
        val swipeGesture = object:  ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or  ItemTouchHelper.RIGHT)
        {
            //Target recycler viewHolder to make it move
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            //Target recycler viewHolder and direction to make it swipe
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if(direction == ItemTouchHelper.LEFT){
                        val contactToDelete = adapter.getContactWithPosition(viewHolder.adapterPosition)
                        adapter.deleteContact(contactToDelete.id, this@MainActivity)
                    }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(contactRecyclerView)




        //setting floating button to open add_contact activity
        val addContactBtn = findViewById<FloatingActionButton>(R.id.add_contact_btn)
        addContactBtn.setOnClickListener {
            val intent = Intent(this,MainActivity3::class.java)
           startActivity(intent)
        }
    }
    //function that get user's data from our firebass database
    private fun getUserDataFromDatabase() {
        dbref = FirebaseDatabase.getInstance().getReference("Contact")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    contactList.clear()
                    for (contactSnapshot in snapshot.children){
                        val contact = contactSnapshot.getValue(ContactData::class.java)
                        contactList.add(contact!!)
                    }
                    contactRecyclerView.adapter = ContactRecyclerViewAdapter(contactList, this@MainActivity, this@MainActivity)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    // overriding onclick listener and using intent to pass data to other activity
    override fun onItemClick(position: Int, myView: View?) {
        val intent = Intent(this,DisplayContact::class.java)

        intent.putExtra("firstname", contactList[position].firstName)
        intent.putExtra("mobile", contactList[position].mobile)
        intent.putExtra("email", contactList[position].email)
        intent.putExtra("lastname", contactList[position].lastName)
        startActivity(intent)
    }

    //displaying Update Dialog
    private fun showUpdateDialog(id:String, firstname:String, lastname:String, mobile:String, email:String) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(this)

        // set the custom layout
        val view = layoutInflater.inflate(R.layout.edit_form, null)

        // Get Views references from your layout
        val edit_firstname: EditText = view.findViewById(R.id.edit_firstname)
        val edit_lastname: EditText = view.findViewById(R.id.edit_lastname)
        val edit_mobile: EditText = view.findViewById(R.id.edit_mobile)
        val edit_email: EditText = view.findViewById(R.id.edit_email)
        val edit_button: Button = view.findViewById(R.id.updateBtn)
        val back_btn: ImageButton = view.findViewById(R.id.backBtn)

        dialog?.setTitle("Dialog Title")

        //on back button press
        back_btn.setOnClickListener {
            val backIntent = Intent(this,MainActivity::class.java)
            startActivity(backIntent)
        }

        edit_button.setOnClickListener{
            val firstname = edit_firstname.text.toString().trim()
            val lastname = edit_lastname.text.toString().trim()
            val mobile = edit_mobile.text.toString().trim()
            val email = edit_email.text.toString().trim()

            //check for empty input
            if(firstname.isEmpty() && lastname.isEmpty() && mobile.isEmpty() && email.isEmpty()){
                edit_firstname.setError("Firstname required")
                edit_lastname.setError("Lastname required")
                edit_mobile.setError("Mobile required")
                edit_email.setError("Email required")
                return@setOnClickListener
            }else{
                if (firstname.isEmpty()){
                    edit_firstname.setError("Firstname required")
                    return@setOnClickListener
                }
                if (lastname.isEmpty()){
                    edit_lastname.setError("Lastname required")
                    return@setOnClickListener
                }
                if (mobile.isEmpty()){
                    edit_mobile.setError("Mobile required")
                    return@setOnClickListener
                }
                if (email.isEmpty()){
                    edit_email.setError("Email required")
                    return@setOnClickListener
                }
            }
            //updating Contact
            updateContact(id, firstname, firstname.first().uppercaseChar().toString(), lastname, mobile, email)
            edit_button.text = "Updated Successfully"
            //dialog?.dismiss()

            //Load Contact List
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        builder.setView(view)

        // create and show the alert dialog
        dialog = builder.create()
        dialog.show()
    }

    //Contact update function
    private fun updateContact(id:String, firstname:String, firstletter:String, lastname:String, mobile:String, email:String): Boolean {
        dbref = FirebaseDatabase.getInstance().getReference("Contact").child(id)
        var contact = ContactData(id, firstname, firstletter, lastname, mobile, email)
        dbref.setValue(contact)
        Toast.makeText(this, "Contact Successfully Updated", Toast.LENGTH_LONG).show()
        return true
    }


    //On long Pressed contact update function
    override fun onLongItemClick(position: Int, myView: View?) {
        val contact = contactList[position]
        showUpdateDialog(contact.id, contact.firstName, contact.lastName,
            contact.mobile, contact.email)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}
