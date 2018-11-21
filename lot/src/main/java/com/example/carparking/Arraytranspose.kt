package com.example.carparking

fun main(args:Array<String>){

  val row =   2
  val column = 3

  val matrix = arrayOf(intArrayOf(2,4,5), intArrayOf(5,6,7))
 // display(matrix)


  val transpose = Array(column){IntArray(row)}

  for(i in 0..row-1){

    for(j in 0..column-1 ){

      transpose[j][i] = matrix[i][j]
    }
  }

 //display(transpose)

}
/*
fun display(matrix){

  println("the matrix is ")

  for(row in matrix){
    for(column in row){
      println("$column")
    }

  }
}*/