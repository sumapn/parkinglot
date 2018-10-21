package com.example.carparking

import org.omg.PortableInterceptor.InvalidSlot

val funMap = mapOf(
    "park" to  ::gotoParkingSlot,
    "remove" to ::leaveFromtheLot,
    "status" to ::statusoftheParkingLot
)

fun main(args : Array<String>) {

  val  carpark = Parkinglot(5)

  while(true) {
    print("$ > ")
    val line = readLine()
    line?: break

    val words: List<String> = line.split(" ")
    if(words[0]=="quit"){
      break
    }

    val handler = funMap[words[0]]
    if (handler == null) {
      println("Invalid command ${words[0]} : Allowed commands ${funMap.keys}")
      continue
    }
    handler(carpark,words)
    carpark.allocateSlot()
    carpark.leaveCar()

  }
}


class Slot(var parked: Boolean, var regNum: String, var colour: String)


class Parkinglot(val capacity : Int) {
  val carSlots = Array(capacity) { Slot(false, "", "") }

  fun allocateSlot() : Int{
     for(i in 0 until carSlots.size) {
     if (!carSlots[i].parked) {
         return i
       }
     }

     return -1
  }

  fun park( regNum: String ,  colour: String, slotId :Int) {
         carSlots[slotId].parked = true
         carSlots[slotId].regNum = regNum
         carSlots[slotId].colour = colour

  }


  fun leaveCar(slotNum: Int) {
    carSlots[slotNum].parked = false
  }


}

fun gotoParkingSlot(carpark: Parkinglot , args: List<String>) {
  val car_reg_num = args[1]
  val car_colour = args[2]

    val slot = carpark.allocateSlot()
    if(slot == -1)
    {
      println("The Parking lot is full")
    }
  else
    {
      carpark.park(car_reg_num,car_colour,slot)
      println("Your car is parked at  slot = $slot ")
    }


  //println(car_reg_num)
  //println(car_colour)
}


fun leaveFromtheLot(carpark: Parkinglot,args: List<String>){
  println("Your Car is  Removed From The Lot :")
  val car_SlotId = args[1]

  val car_int_SlotId = car_SlotId.toInt()


  //val slot = carpark.allocateSlot()
  carpark.leaveCar(car_int_SlotId)
}

fun statusoftheParkingLot(carpark: Parkinglot, args: List<String>) {
    for(i in  0 until carpark.carSlots.size){
      val slot = carpark.carSlots[i]
      if (slot.parked) {
        println(slot.regNum)
        println(slot.colour)
        println(i)
      }
    }
}
