package com.example.carparking

val funMap = mapOf(
    "park" to ::gotoParkingSlot,
    "remove" to ::leaveFromtheLot,
    "status" to ::statusoftheParkingLot
)

fun main(args: Array<String>) {
  val carpark = Parkinglot(5)

  while (true) {
    print("$ > ")
    val line = readLine()
    line ?: break

    val words: List<String> = line.split(" ")
    if (words[0] == "quit") {
      break
    }

    val handler = funMap[words[0]]
    if (handler == null) {
      println("Invalid command ${words[0]} : Allowed commands ${funMap.keys}")
      continue
    }
    handler(carpark, words)
  }
}


class Slot(var parked: Boolean, var regNum: String, var colour: String)


class Parkinglot(capacity: Int) {
  val carSlots = Array(capacity) { Slot(false, "", "") }

  fun allocateSlot(): Int {
    for (i in 0 until carSlots.size) {
      if (!carSlots[i].parked) {
        return i
      }
    }

    return -1
  }

  fun park(regNum: String, colour: String, slotId: Int) {
    carSlots[slotId].parked = true
    carSlots[slotId].regNum = regNum
    carSlots[slotId].colour = colour
  }

  fun leaveCar(slotNum: Int) {
    carSlots[slotNum].parked = false
    if (carSlots.size < slotNum) {
      throw Exception("Slot capacity is Exceeds the available limit")

    }
  }
}

fun gotoParkingSlot(carpark: Parkinglot, args: List<String>) {
  val car_reg_num = args[1]
  val car_colour = args[2]

  val slot = carpark.allocateSlot()
  if (slot == -1) {
    println("The Parking lot is full")
  } else {
    carpark.park(car_reg_num, car_colour, slot)
    val user_slot = slot + 1
    println("Your car is parked at  slot = $user_slot ")
  }
}


fun leaveFromtheLot(carpark: Parkinglot, args: List<String>) {
  val car_SlotId = args[1]

  val car_int_SlotId = car_SlotId.toInt()
  carpark.leaveCar(car_int_SlotId)

  val user_slot = car_int_SlotId - 1
  println("Your car is removed  from the  slot = $user_slot ")

}

fun statusoftheParkingLot(carpark: Parkinglot, args: List<String>) {
  for (i in 0 until carpark.carSlots.size) {
    val slot = carpark.carSlots[i]
    if (slot.parked) {
      println(slot.regNum)
      println(slot.colour)
      println(i)
    }
  }
}
