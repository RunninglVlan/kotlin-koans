package i_introduction._7_Nullable_Types

import org.junit.Assert.assertEquals
import org.junit.Test

class N07NullableTypesKtTest {
	fun testSendMessageToClient(
			client: Client?,
			expectedMessage: String?,
			expectedEmail: String? = null,
			shouldBeInvoked: Boolean = false
	) {
		var invoked = false
		sendMessageToClient(client, expectedMessage, object : Mailer {
			override fun sendMessage(email: String, message: String) {
				invoked = true
				assertEquals("The message is not as expected:",
						expectedMessage, message)
				assertEquals("The email is not as expected:",
						expectedEmail, email)
			}
		})
		assertEquals("The function 'sendMessage' should${if (shouldBeInvoked) "" else "n't"} be invoked",
				shouldBeInvoked, invoked)
	}

	@Test
	fun everythingIsOk() {
		testSendMessageToClient(Client(PersonalInfo("bob@gmail.com")),
				"Hi Bob! We have an awesome proposition for you...",
				"bob@gmail.com",
				true)
	}

	@Test
	fun noMessage() {
		testSendMessageToClient(Client(PersonalInfo("bob@gmail.com")), null)
	}

	@Test
	fun noEmail() {
		testSendMessageToClient(Client(PersonalInfo(null)), "Hi Bob! We have an awesome proposition for you...")
	}

	@Test
	fun noPersonalInfo() {
		testSendMessageToClient(Client(null), "Hi Bob! We have an awesome proposition for you...")
	}

	@Test
	fun noClient() {
		testSendMessageToClient(null, "Hi Bob! We have an awesome proposition for you...")
	}
}