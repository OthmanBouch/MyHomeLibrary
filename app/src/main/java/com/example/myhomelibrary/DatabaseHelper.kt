package com.example.myhomelibrary

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.TableLayout
import androidx.core.content.contentValuesOf

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        //database information
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MHL"
        //table's information
            //user table attributes
        private const val TABLE_USER = "users"
        private const val COL_USER_ID = "id"
        private const val COL_USER_NAME = "name"
        private const val COL_USER_EMAIL = "email"
        private const val COL_USER_PASSWORD = "password"
        private const val COL_USER_ROOM = "room"
        private const val COL_USER_EVENT = "event"
                //book table attributes
        private const val TABLE_BOOKS = "books"
        private const val COL_BOOK_ID = "id"
        private const val COL_BOOK_TITLE = "title"
        private const val COL_BOOK_AUTHOR = "author"
        private const val COL_BOOK_FIELD = "field"
        private const val COL_BOOK_STATUS = "status"
        private const val COL_BOOK_SHELF_NUM = "shelf_num"
        private const val COL_BOOK_USER_ID = "user_id"
                //room table attributes
        private const val TABLE_ROOMS = "rooms"
        private const val COL_ROOM_ID = "id"
        private const val COL_ROOM_LABEL = "label"
                //shelf table attributes
        private const val TABLE_SHELVES = "shelves"
        private const val COL_SHELF_ID = "id"
        private const val COL_SHELF_LABEL = "label"
        private const val COL_SHELF_ROOM_NUM ="room_num"
                //guest table attributes
        private const val TABLE_GUESTS = "guests"
        private const val COL_GUEST_ID = "id"
        private const val COL_GUEST_NAME = "name"
        private const val COL_GUEST_JOB ="job"
                //event table attributes
        private const val  TABLE_EVENTS = "events"
        private const val COL_EVENT_ID = "id"
        private const val COL_EVENT_NAME = "name"
        private const val COL_EVENT_GUEST ="guest"


    }

    private val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
            "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " $COL_USER_NAME TEXT," +
            " $COL_USER_EMAIL TEXT," +
            " $COL_USER_PASSWORD TEXT," +
            " $COL_USER_ROOM INTEGER," +
            " $COL_USER_EVENT INTEGER)"

    private val CREATE_TABLE_BOOKS = "CREATE TABLE $TABLE_BOOKS (" +
            "$COL_BOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " $COL_BOOK_TITLE TEXT," +
            " $COL_BOOK_AUTHOR TEXT," +
            " $COL_BOOK_FIELD TEXT," +
            " $COL_BOOK_STATUS TEXT," +
            " $COL_BOOK_SHELF_NUM INTEGER," +
            " $COL_BOOK_USER_ID INTEGER)"

    private val CREATE_TABLE_ROOMS = "CREATE TABLE $TABLE_ROOMS (" +
            "$COL_ROOM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " $COL_ROOM_LABEL TEXT)"

    private val CREATE_TABLE_SHELVES = "CREATE TABLE $TABLE_SHELVES (" +
            "$COL_SHELF_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COL_SHELF_LABEL TEXT," +
            "$COL_SHELF_ROOM_NUM INTEGER)"

    private val CREATE_TABLE_GUESTS = "CREATE TABLE $TABLE_GUESTS (" +
            "$COL_GUEST_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COL_GUEST_NAME TEXT," +
            "$COL_GUEST_JOB TEXT)"

    private val CREATE_TABLE_EVENTS = "CREATE TABLE $TABLE_EVENTS (" +
            "$COL_EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COL_EVENT_NAME TEXT," +
            "$COL_EVENT_GUEST)"

    private val DROP_TABLE_GUESTS = "DROP TABLE IF EXISTS $TABLE_GUESTS"
    private val DROP_TABLE_SHELVES = "DROP TABLE IF EXISTS $TABLE_SHELVES"
    private val DROP_TABLE_ROOMS = "DROP TABLE IF EXISTS $TABLE_ROOMS"
    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"
    private val DROP_TABLE_BOOKS = "DROP TABLE IF EXISTS $TABLE_BOOKS"
    private val DROP_TABLE_EVENTS = "DROP TABLE IF EXISTS $TABLE_EVENTS"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)
        db?.execSQL(CREATE_TABLE_BOOKS)
        db?.execSQL(CREATE_TABLE_ROOMS)
        db?.execSQL(CREATE_TABLE_SHELVES)
        db?.execSQL(CREATE_TABLE_GUESTS)
        db?.execSQL(CREATE_TABLE_EVENTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DROP_TABLE_USER)
        db?.execSQL(DROP_TABLE_BOOKS)
        db?.execSQL(DROP_TABLE_ROOMS)
        db?.execSQL(DROP_TABLE_SHELVES)
        db?.execSQL(DROP_TABLE_GUESTS)
        db?.execSQL(DROP_TABLE_EVENTS)
        onCreate(db)
    }
//add object parts
    fun registerUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_USER_NAME, user.name)
        values.put(COL_USER_EMAIL, user.email)
        values.put(COL_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, values)
        db.close()
    }

    fun AddBook(book: Book){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_BOOK_TITLE, book.title)
        values.put(COL_BOOK_AUTHOR, book.author)
        values.put(COL_BOOK_FIELD, book.field)
        values.put(COL_BOOK_STATUS, book.status)

        db.insert(TABLE_BOOKS, null, values)
        db.close()
    }
    fun AddRoom(room: Room){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ROOM_LABEL, room.label)
        db.insert(TABLE_ROOMS, null, values)
        db.close()
    }
    fun AddShelf(shelf: Shelf){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_SHELF_LABEL,shelf.label)
        values.put(COL_SHELF_ROOM_NUM,shelf.room)
        db.insert(TABLE_SHELVES,null,values)
        db.close()
    }
    fun AddGuest(guest: Guest){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_GUEST_NAME, guest.name)
        values.put(COL_GUEST_JOB, guest.job)

        db.insert(TABLE_GUESTS, null, values)
        db.close()
    }
    fun AddEvent(event: Event){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_EVENT_NAME, event.name)
        values.put(COL_EVENT_GUEST, event.guest)

        db.insert(TABLE_EVENTS, null, values)
        db.close()
    }

//login part
    fun loginUser(name: String, pass: String): Boolean {
        val columns = arrayOf(COL_USER_ID)
        val db = this.readableDatabase
        val selection = "$COL_USER_NAME = ? AND $COL_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(name, pass)

        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }
// get data part
    fun getAllUsersAccount(): List<User> {
        val userList = ArrayList<User>()
        val db = this.readableDatabase
        val columns = arrayOf(COL_USER_ID, COL_USER_NAME, COL_USER_EMAIL,
            COL_USER_PASSWORD, COL_USER_ROOM, COL_USER_EVENT)
        val sortOrder = "$COL_USER_ID ASC"

        val cursor = db.query(
            TABLE_USER,
            columns,
            null,
            null,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val userId = it.getInt(it.getColumnIndexOrThrow(COL_USER_ID))
                val userName = it.getString(it.getColumnIndexOrThrow(COL_USER_NAME))
                val userEmail = it.getString(it.getColumnIndexOrThrow(COL_USER_EMAIL))
                val password = it.getString(it.getColumnIndexOrThrow(COL_USER_PASSWORD))
                val userRoom = it.getInt(it.getColumnIndexOrThrow(COL_USER_ROOM))
                val userEvent = it.getInt(it.getColumnIndexOrThrow(COL_USER_EVENT))

                val user = User(userId, userName, userEmail, password,userRoom, userEvent)
                userList.add(user)
            }
        }

        db.close()
        return userList
    }

    fun getAllBooksRecords(): List<Book> {
        val bookList = ArrayList<Book>()
        val db = this.readableDatabase
        val columns = arrayOf(
            COL_BOOK_ID, COL_BOOK_TITLE, COL_BOOK_AUTHOR,
            COL_BOOK_FIELD, COL_BOOK_STATUS, COL_BOOK_SHELF_NUM,
            COL_BOOK_USER_ID)
        val sortOrder = "$COL_BOOK_ID ASC"

        val cursor = db.query(
            TABLE_BOOKS,
            columns,
            null,
            null,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val bookId = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_ID))
                val bookTitle = it.getString(it.getColumnIndexOrThrow(COL_BOOK_TITLE))
                val bookAuthor = it.getString(it.getColumnIndexOrThrow(COL_BOOK_AUTHOR))
                val bookField = it.getString(it.getColumnIndexOrThrow(COL_BOOK_FIELD))
                val bookStatus = it.getString(it.getColumnIndexOrThrow(COL_BOOK_STATUS))
                val bookShelf = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_SHELF_NUM))
                val bookUser = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_USER_ID))

                val     book = Book(bookId, bookTitle, bookAuthor, bookField, bookStatus, bookShelf, bookUser)
                bookList.add(book)
            }
        }

        db.close()
        return bookList
    }


    fun getAllRoomsRecords(): List<Room> {
        val roomList = ArrayList<Room>()
        val db = this.readableDatabase
        val columns = arrayOf(
            COL_ROOM_ID, COL_ROOM_LABEL)
        val sortOrder = "$COL_ROOM_ID ASC"

        val cursor = db.query(
            TABLE_ROOMS,
            columns,
            null,
            null,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val roomId = it.getInt(it.getColumnIndexOrThrow(COL_ROOM_ID))
                val roomLabel = it.getString(it.getColumnIndexOrThrow(COL_ROOM_LABEL))
                val     room = Room(roomId, roomLabel)
                roomList.add(room)
            }
        }

        db.close()
        return roomList
    }

    fun getAllShelvesRecords(): List<Shelf> {
        val shelfList = ArrayList<Shelf>()
        val db = this.readableDatabase
        val columns = arrayOf(
            COL_SHELF_ID, COL_SHELF_LABEL, COL_SHELF_ROOM_NUM)
        val sortOrder = "$COL_SHELF_ID ASC"

        val cursor = db.query(
            TABLE_SHELVES,
            columns,
            null,
            null,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val shelfId = it.getInt(it.getColumnIndexOrThrow(COL_SHELF_ID))
                val shelfLabel = it.getString(it.getColumnIndexOrThrow(COL_SHELF_LABEL))
                val shelfroom = it.getInt(it.getColumnIndexOrThrow(COL_SHELF_ROOM_NUM))

                val shelf = Shelf(shelfId, shelfLabel, shelfroom)
                shelfList.add(shelf)
            }
        }

        db.close()
        return shelfList
    }

    fun getAllGuestsRecords(): List<Guest> {
        val guestList = ArrayList<Guest>()
        val db = this.readableDatabase
        val columns = arrayOf(
            COL_GUEST_ID, COL_GUEST_NAME, COL_GUEST_JOB)
        val sortOrder = "$COL_GUEST_ID ASC"

        val cursor = db.query(
            TABLE_GUESTS,
            columns,
            null,
            null,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val guestId = it.getInt(it.getColumnIndexOrThrow(COL_GUEST_ID))
                val guestName = it.getString(it.getColumnIndexOrThrow(COL_GUEST_NAME))
                val guestJob = it.getString(it.getColumnIndexOrThrow(COL_GUEST_JOB))

                val guest = Guest(guestId, guestName, guestJob)
                guestList.add(guest)
            }
        }

        db.close()
        return guestList
    }

    fun getAllEventsRecords(): List<Event> {
        val eventList = ArrayList<Event>()
        val db = this.readableDatabase
        val columns = arrayOf(
            COL_EVENT_ID, COL_EVENT_NAME, COL_EVENT_GUEST)
        val sortOrder = "$COL_EVENT_ID ASC"

        val cursor = db.query(
            TABLE_EVENTS,
            columns,
            null,
            null,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val eventId = it.getInt(it.getColumnIndexOrThrow(COL_EVENT_ID))
                val eventName = it.getString(it.getColumnIndexOrThrow(COL_EVENT_NAME))
                val eventGuest = it.getInt(it.getColumnIndexOrThrow(COL_EVENT_GUEST))

                val event = Event(eventId, eventName, eventGuest)
                eventList.add(event)
            }
        }

        db.close()
        return eventList
    }



    fun getAllBooksUserViewRecords(): List<Book_UserView> {
        val bookList = ArrayList<Book_UserView>()
        val db = this.readableDatabase
        val columns = arrayOf(
            COL_BOOK_ID, COL_BOOK_TITLE, COL_BOOK_AUTHOR,
            COL_BOOK_FIELD, COL_BOOK_STATUS, COL_BOOK_SHELF_NUM)
        val selection = "$COL_BOOK_STATUS = ?"
        val selectionArgs = arrayOf("available")
        val sortOrder = "$COL_BOOK_ID ASC"

        val cursor = db.query(
            TABLE_BOOKS,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val bookId = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_ID))
                val bookTitle = it.getString(it.getColumnIndexOrThrow(COL_BOOK_TITLE))
                val bookAuthor = it.getString(it.getColumnIndexOrThrow(COL_BOOK_AUTHOR))
                val bookField = it.getString(it.getColumnIndexOrThrow(COL_BOOK_FIELD))
                val bookStatus = it.getString(it.getColumnIndexOrThrow(COL_BOOK_STATUS))
                val bookShelf = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_SHELF_NUM))

                val book = Book_UserView(bookId, bookTitle, bookAuthor, bookField, bookStatus, bookShelf)
                bookList.add(book)
            }
        }

        db.close()
        return bookList
    }
    fun getBookUserResViewRecords(username: String): List<Book_UserResView> {
        val bookList = ArrayList<Book_UserResView>()
        val db = this.readableDatabase
        val columns = arrayOf(
            COL_BOOK_ID, COL_BOOK_TITLE, COL_BOOK_SHELF_NUM, COL_BOOK_USER_ID)
        val userQuery = "SELECT $COL_USER_ID FROM $TABLE_USER WHERE $COL_USER_NAME = '$username'"
        val selection = "$COL_BOOK_USER_ID = ($userQuery)"
        val sortOrder = "$COL_BOOK_ID ASC"

        val cursor = db.query(
            TABLE_BOOKS,
            columns,
            selection,
            null,
            null,
            null,
            sortOrder
        )

        cursor.use {
            while (it.moveToNext()) {
                val bookId = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_ID))
                val bookTitle = it.getString(it.getColumnIndexOrThrow(COL_BOOK_TITLE))
                val bookShelf = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_SHELF_NUM))
                val bookUserId = it.getInt(it.getColumnIndexOrThrow(COL_BOOK_USER_ID))

                val bookUserResView = Book_UserResView(bookId, bookTitle, bookShelf, bookUserId)
                bookList.add(bookUserResView)
            }
        }

        db.close()
        return bookList
    }



    fun getReservedRoomsForUser(username: String): List<Room> {
        val db = this.readableDatabase
        val columns = arrayOf(COL_ROOM_ID, COL_ROOM_LABEL)

        val cursor = db.query(
            TABLE_ROOMS,
            columns,
            "$COL_ROOM_ID IN (SELECT $COL_USER_ROOM FROM $TABLE_USER WHERE $COL_USER_NAME = ?)",
            arrayOf(username),
            null,
            null,
            null
        )

        val roomList = ArrayList<Room>()

        cursor.use {
            while (it.moveToNext()) {
                val roomId = it.getInt(it.getColumnIndexOrThrow(COL_ROOM_ID))
                val roomLabel = it.getString(it.getColumnIndexOrThrow(COL_ROOM_LABEL))
                val room = Room(roomId, roomLabel)
                roomList.add(room)
            }
        }

        db.close()
        return roomList
    }

    fun getReservedEventsForUser(username: String): List<Event> {
        val db = this.readableDatabase
        val columns = arrayOf(COL_EVENT_ID, COL_EVENT_NAME, COL_EVENT_GUEST)

        val cursor = db.query(
            TABLE_EVENTS,
            columns,
            "$COL_EVENT_ID IN (SELECT $COL_USER_EVENT FROM $TABLE_USER WHERE $COL_USER_NAME = ?)",
            arrayOf(username),
            null,
            null,
            null
        )

        val eventList = ArrayList<Event>()

        cursor.use {
            while (it.moveToNext()) {
                val eventId = it.getInt(it.getColumnIndexOrThrow(COL_EVENT_ID))
                val eventName = it.getString(it.getColumnIndexOrThrow(COL_EVENT_NAME))
                val eventGuest = it.getInt(it.getColumnIndexOrThrow(COL_EVENT_GUEST))
                val event = Event(eventId, eventName, eventGuest)
                eventList.add(event)
            }
        }

        db.close()
        return eventList
    }






    // delete part
    fun deleteUser(userId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COL_USER_ID = ?"
        val selectionArgs = arrayOf(userId.toString())

        val deletedRows = db.delete(TABLE_USER, selection, selectionArgs)
        db.close()

        return deletedRows > 0
    }
    fun deleteBook(bookId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COL_BOOK_ID = ?"
        val selectionArgs = arrayOf(bookId.toString())

        val deletedRows = db.delete(TABLE_BOOKS, selection, selectionArgs)
        db.close()

        return deletedRows > 0
    }
    fun deleteRoom(roomId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COL_ROOM_ID = ?"
        val selectionArgs = arrayOf(roomId.toString())

        val deletedRows = db.delete(TABLE_ROOMS, selection, selectionArgs)
        db.close()

        return deletedRows > 0
    }
    fun deleteShelf(shelfId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COL_SHELF_ID = ?"
        val selectionArgs = arrayOf(shelfId.toString())

        val deletedRows = db.delete(TABLE_SHELVES, selection, selectionArgs)
        db.close()

        return deletedRows > 0
    }
    fun deleteGuest(guestId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COL_GUEST_ID = ?"
        val selectionArgs = arrayOf(guestId.toString())

        val deletedRows = db.delete(TABLE_GUESTS, selection, selectionArgs)
        db.close()

        return deletedRows > 0
    }
    fun deleteEvent(eventId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COL_EVENT_ID = ?"
        val selectionArgs = arrayOf(eventId.toString())

        val deletedRows = db.delete(TABLE_EVENTS, selection, selectionArgs)
        db.close()

        return deletedRows > 0
    }
  // reservation part

    fun reserveBookAndUpdateStatus(bookId: Int, userName: String): Int {
        val db = this.writableDatabase
        val userQuery = "SELECT $COL_USER_ID FROM $TABLE_USER WHERE $COL_USER_NAME = '$userName'"
        val userCursor = db.rawQuery(userQuery, null)
        var userId = 0

        if (userCursor.moveToFirst()) {
            userId = userCursor.getInt(userCursor.getColumnIndexOrThrow(COL_USER_ID))
        }
        userCursor.close()

        val contentValues = ContentValues()
        contentValues.put(COL_BOOK_STATUS, "Not Available")
        contentValues.put(COL_BOOK_USER_ID, userId)

        val updatedRows = db.update(TABLE_BOOKS, contentValues, "$COL_BOOK_ID = ?", arrayOf(bookId.toString()))
        db.close()

        return updatedRows
    }

                            //also used for remove by setting roomId to 0
    fun reserveRoomWithUser(userName: String, roomId: Int): Int {
        val db = this.writableDatabase
        val userQuery = "SELECT $COL_USER_ID FROM $TABLE_USER WHERE $COL_USER_NAME = '$userName'"
        val userCursor = db.rawQuery(userQuery, null)
        var userId = 0

        if (userCursor.moveToFirst()) {
            userId = userCursor.getInt(userCursor.getColumnIndexOrThrow(COL_USER_ID))
        }
        userCursor.close()

        val contentValues = ContentValues()
        contentValues.put(COL_USER_ROOM, roomId)

        val updatedRows = db.update(TABLE_USER, contentValues, "$COL_USER_ID = ?", arrayOf(userId.toString()))
        db.close()

        return updatedRows
    }

                                //also used for remove by giving event id =0
    fun reserveEventWithUser(userName: String, eventId: Int): Int {
        val db = this.writableDatabase
        val userQuery = "SELECT $COL_USER_ID FROM $TABLE_USER WHERE $COL_USER_NAME = '$userName'"
        val userCursor = db.rawQuery(userQuery, null)
        var userId = 0

        if (userCursor.moveToFirst()) {
            userId = userCursor.getInt(userCursor.getColumnIndexOrThrow(COL_USER_ID))
        }
        userCursor.close()

        val contentValues = ContentValues()
        contentValues.put(COL_USER_EVENT, eventId)

        val updatedRows = db.update(TABLE_USER, contentValues, "$COL_USER_ID = ?", arrayOf(userId.toString()))
        db.close()

        return updatedRows
    }


    // remove from reservation part
    fun removeReservation(bookId: Int) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_BOOK_STATUS, "available")
            put(COL_BOOK_USER_ID, 0)
        }
        val selection = "$COL_BOOK_ID = ?"
        val selectionArgs = arrayOf(bookId.toString())

        db.update(
            TABLE_BOOKS,
            values,
            selection,
            selectionArgs
        )

        db.close()
    }



    // update part






}
