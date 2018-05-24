package iii_conventions

import java.util.*

fun MyDate.nextDay() = addTimeIntervals(TimeUnit.DAY, 1)

fun MyDate.addTimeIntervals(timeUnit: TimeUnit, number: Int) = Calendar.getInstance().run {
	set(year, month, dayOfMonth)
	add(when (timeUnit) {
		TimeUnit.DAY -> Calendar.DAY_OF_MONTH
		TimeUnit.WEEK -> Calendar.WEEK_OF_MONTH
		TimeUnit.YEAR -> Calendar.YEAR
	}, number)
	MyDate(get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DATE))
}