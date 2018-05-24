package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
	override fun compareTo(other: MyDate): Int {
		return when {
			this.year != other.year -> this.year - other.year
			this.month != other.month -> this.month - other.month
			else -> this.dayOfMonth - other.dayOfMonth
		}
	}

	operator fun rangeTo(other: MyDate): DateRange = DateRange(this, other)
	operator fun plus(unit: TimeUnit) = addTimeIntervals(unit, 1)
	operator fun plus(interval: TimeInterval) = addTimeIntervals(interval.unit, interval.number)
}

enum class TimeUnit {
	DAY,
	WEEK,
	YEAR;

	operator fun times(number: Int) = TimeInterval(this, number)
}

class TimeInterval(val unit: TimeUnit, val number: Int)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
	override fun iterator() = object : Iterator<MyDate> {
		var current: MyDate = start

		override fun hasNext() = current <= endInclusive

		override fun next(): MyDate {
			val next = current
			current = current.nextDay()
			return next
		}
	}
}
