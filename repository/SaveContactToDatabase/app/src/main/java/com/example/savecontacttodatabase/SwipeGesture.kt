//package com.example.savecontacttodatabase
//
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//
//abstract class SwipeGesture: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or  ItemTouchHelper.RIGHT) {
//
//    override fun onMove(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        target: RecyclerView.ViewHolder
//    ): Boolean {
//        return false
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        TODO("Not yet implemented")
//    }
//}
////
////private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
////
/////**
//// *
////Make sure you pass in your RecyclerAdapter to this class
////
//// */
////public CallBack(int dragDirs, int swipeDirs, RecyclerAdapter adapter) {
////    super(dragDirs, swipeDirs);
////    this.adapter = adapter;
////}
////
////@Override
////public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
////    return false;
////}
////
////@Override
////public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
////    int position = viewHolder.getAdapterPosition(); // this is how you can get the position
////    Object object = adapter.getObject(position); // You will have your own class ofcourse.
////
////    // then you can delete the object
////    root.child("Object").child(object.getId()).setValue(null);// setting the value to null will just delete it from the database.