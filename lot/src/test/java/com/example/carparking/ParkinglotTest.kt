package com.example.carparking

import junit.framework.Assert.assertEquals
import org.junit.Test

internal class ParkinglotTest {

  @Test
  fun allocaterAllocatesZeroFirst() {
    val parkinglot = Parkinglot(100)
    val slot = parkinglot.allocateSlot()
    assertEquals(0, slot)
  }


  @Test
  fun returnsMinusOneIfSlotIsFull() {
    val parkinglot = Parkinglot(1)
    val slot = parkinglot.allocateSlot()
    parkinglot.park("ka01", "red", slot)
    val full = parkinglot.allocateSlot()
    assertEquals(-1, full)
  }

  @Test
  fun onceCarIsRemovedItisAvailableForNextAllocation(){
    val parkinglot = Parkinglot( 1)
    val slot = parkinglot.allocateSlot()
    parkinglot.park("ka00","red",slot)
    val full = parkinglot.allocateSlot()
    assertEquals(-1,full)
    parkinglot.leaveCar(slot)
    val slot2 = parkinglot.allocateSlot()
    assertEquals(slot, slot2)
  }
}