package ii_collections

fun example5() {
	val result = listOf("a", "bbb", "cc").sortedBy { it.length }

	assert(result == listOf("a", "cc", "bbb"))
}

fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> {
	// Return a list of customers, sorted by the ascending number of orders they made
	return customers.sortedBy { it.orders.size }
}
