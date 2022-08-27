package parking

var parkingSpace =  MutableList(0) { "" }
var count = 0

fun main() {
    while (true) {
        val userChoice = readln().split(" ")
        when (userChoice[0].lowercase()) {
            "park" -> parkingCar(userChoice)
            "leave" -> carLeaving(userChoice)
            "create" -> newSlotsCreated(userChoice)
            "status" -> printSpotsStatus()
            "reg_by_color" -> printCarByColor(userChoice[1].uppercase())
            "spot_by_color" -> printSpotByColor(userChoice[1].uppercase())
            "spot_by_reg" -> printSpotByReg(userChoice[1].uppercase())
            "exit" -> break
            else -> println("please enter \"park\", \"leave\" or \"exit\" ")
        }
    }
}

fun parkingCar(userChoice: List<String>) {
    when (parkingSpace.size > 0) {
         true -> {
            when {
                parkingSpace.contains("") -> {
                    count++
                    val freeSpot = parkingSpace.indexOf("")
                    parkingSpace[freeSpot] = "$count ${userChoice[1].uppercase()} ${userChoice[2].uppercase()}"
                    println("${userChoice[2].uppercase()} car parked in spot ${freeSpot + 1}.")
                }
                else -> println("Sorry, the parking lot is full.")
            }
        }
        else -> {
            println("Sorry, a parking lot has not been created.")
        }
    }
}

fun carLeaving(userChoice: List<String>) {
    when (parkingSpace.size > 0 ) {
        true -> {
            val spaceNumber = userChoice[1].toInt() - 1
            when {
                parkingSpace[spaceNumber].isEmpty() -> println("There is no car in spot ${userChoice[1]}.")
                else -> {
                    parkingSpace[spaceNumber] = ""
                    println("Spot ${userChoice[1]} is free.")
                }
            }
        }
        else -> {
            println("Sorry, a parking lot has not been created.")
        }
    }
}

fun  newSlotsCreated(userChoice: List<String>) {
    parkingSpace =  MutableList(0) { "" }
    count = 0
    val numberOfNewSpotsCreated = userChoice[1].toInt()
    when (numberOfNewSpotsCreated >= 1) {
         true -> {
            parkingSpace = MutableList(numberOfNewSpotsCreated) { "" }
            println("Created a parking lot with ${userChoice[1]} spots.")
        }
        else -> println("${userChoice[1]} is invalid: one can not create spots less than 1")
    }
}

fun printSpotsStatus() {
    when (parkingSpace.size > 0) {
         true -> {
            when (count) {
                0 -> println("Parking lot is empty.")
                else -> {
                    for (spot in parkingSpace) {
                        if (spot != "") {
                            println(spot)
                        }
                    }
                }
            }
        }
        else -> println("Sorry, a parking lot has not been created.")
    }
}

fun printCarByColor(carColor: String) {
    var carColorFound = 0
    val regByColor =  MutableList(0) { "" }
    when (parkingSpace.size > 0) {
        true -> {
            when (count) {
                0 -> println("Parking lot is empty.")
                else -> {
                    for (spot in parkingSpace) {
                        if (spot != "" && spot.contains(carColor)) {
                            val spotDetails = spot.split(" ") // numSpot, regNumber, carColor
                            regByColor.add(spotDetails[1]) // regNumber
                            carColorFound++
                        }
                    }
                    if (carColorFound == 0) {
                        println("No cars with color $carColor were found.")
                    } else {
                        println(regByColor.joinToString())
                    }
                }
            }
        }
        else -> println("Sorry, a parking lot has not been created.")
    }
}

fun printSpotByColor(carColor: String) {
    var carColorFound = 0
    val spotByColor =  MutableList(0) { "" }
    when (parkingSpace.size > 0) {
        true -> {
            when (count) {
                0 -> println("Parking lot is empty.")
                else -> {
                    for (spot in parkingSpace) {
                        if (spot != "" && spot.contains(carColor)) {
                            val spotDetails = spot.split(" ") // numSpot, regNumber, carColor
                            spotByColor.add(spotDetails[0]) // numSpot
                            carColorFound++
                        }
                    }
                    if (carColorFound == 0) {
                        println("No cars with color $carColor were found.")
                    } else {
                        println(spotByColor.joinToString())
                    }
                }
            }
        }
        else -> println("Sorry, a parking lot has not been created.")
    }
}

fun printSpotByReg(carReg: String) {
    var carSpotReg = 0
    val spotByReg =  MutableList(0) { "" }
    when (parkingSpace.size > 0) {
        true -> {
            when (count) {
                0 -> println("Parking lot is empty.")
                else -> {
                    for (spot in parkingSpace) {
                        val spotDetails = spot.split(" ") // numSpot, regNumber, carColor
                        if (spot != "" && spotDetails[1] == carReg) {
                            spotByReg.add(spotDetails[0]) // numSpot
                            carSpotReg++
                        }
                    }
                    if (carSpotReg == 0) {
                        println("No cars with registration number $carReg were found.")
                    } else {
                        println(spotByReg.joinToString())
                    }
                }
            }
        }
        else -> println("Sorry, a parking lot has not been created.")
    }
}