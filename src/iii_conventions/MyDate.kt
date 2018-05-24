package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
	override fun compareTo(other: MyDate): Int {
		var result = this.year - other.year
		if (result != 0) {
			return result
		}
		result = this.month - other.month
		if (result != 0) {
			return result
		}
		return this.dayOfMonth - other.dayOfMonth
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
		var current: MyDate? = null

		override fun hasNext() = if (current == null) start <= endInclusive else current!! < endInclusive

		override fun next(): MyDate {
			current = if (current == null) {
				start
			} else {
				current!!.nextDay()
			}
			return current as MyDate
		}
	}
}
