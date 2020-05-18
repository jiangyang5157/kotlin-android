package com.gmail.jiangyang5157.example_router_app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phone: String,
    val email: String?
) : Parcelable

class DummyService {

    companion object {

        var isLoggedIn: Boolean = false

        val contacts = listOf(
            Contact(
                name = "Julian B.",
                phone = "0210000001",
                email = "julian.b@asd.com"
            ),
            Contact(
                name = "Stefan K.",
                phone = "0210000002",
                email = "stefan.k@asd.com"
            ),
            Contact(
                name = "Malte B.",
                phone = "0210000003",
                email = "malte.b@asd.com"
            ),
            Contact(
                name = "Klaus N.",
                phone = "0210000004",
                email = "klaus.n@asd.com"
            ),
            Contact(
                name = "Nasir G.",
                phone = "0210000005",
                email = "nasir.g@asd.com"
            ),
            Contact(
                name = "Mathias Q.",
                phone = "0210000006",
                email = "mathias.q@asd.com"
            ),
            Contact(
                name = "Balazs T.",
                phone = "0210000007",
                email = "balazs.t@asd.com"
            ),
            Contact(
                name = "Niko T.",
                phone = "0210000008",
                email = "niko.t@asd.com"
            ),
            Contact(
                name = "Paul K.",
                phone = "0210000009",
                email = "paul.k@asd.com"
            )
        )
    }
}
