package com.example.myhomelibrary

import android.os.Parcel
import android.os.Parcelable

data class User(
     var id: Int = -1,
     var name: String = "",
     var email: String = "",
     var password: String = "",
     var room: Int = 0,
     var event: Int = 0
) : Parcelable {
     constructor(parcel: Parcel) : this(
          parcel.readInt(),
          parcel.readString() ?: "",
          parcel.readString() ?: "",
          parcel.readString() ?: "",
          parcel.readInt(),
          parcel.readInt()
     )

     override fun writeToParcel(parcel: Parcel, flags: Int) {
          parcel.writeInt(id)
          parcel.writeString(name)
          parcel.writeString(email)
          parcel.writeString(password)
          parcel.writeInt(room)
          parcel.writeInt(event)
     }

     override fun describeContents(): Int {
          return 0
     }

     companion object CREATOR : Parcelable.Creator<User> {
          override fun createFromParcel(parcel: Parcel): User {
               return User(parcel)
          }

          override fun newArray(size: Int): Array<User?> {
               return arrayOfNulls(size)
          }
     }
}
