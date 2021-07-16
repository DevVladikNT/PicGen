import java.awt.image.BufferedImage
import java.util.*
import javax.imageio.ImageIO
import java.io.File

private lateinit var resultImage: BufferedImage

fun main(args: Array<String>) {
    greeting()
    val sc = Scanner(System.`in`)
    var correct = false
    var exit = false
    while (!exit) {
        print("\n1 - Generate new symmetry image\n" +
                "2 - Black-white filter\n" +
                "3 - Color shifts filter\n" +
                "4 - Cyberpunk filter\n" +
                "5 - LGBT filter\n" +
                "6 - Defocusing filter\n" +
                "0 - Exit\n" +
                ">> ")
        correct = false
        when (sc.nextLine()) {
            "1" -> {
                correct = true
                try {
                    print("1 - Black-white\n" +
                            "2 - Colored\n" +
                            ">> ")
                    resultImage = CircleGeneration.make(sc.nextLine().toInt())
                } catch (e: Exception) {
                    correct = false
                    println("Incorrect.")
                }
            }
            "2" -> {
                correct = true
                try {
                    print("Enter source image path\n>> ")
                    resultImage = BlackWhiteFilter.make(sc.nextLine())
                } catch (e: Exception) {
                    correct = false
                    println("Error. Maybe your path isn`t correct.")
                }
            }
            "3" -> {
                correct = true
                try {
                    print("Enter source image path\n>> ")
                    resultImage = ColorShiftsFilter.make(sc.nextLine())
                } catch (e: Exception) {
                    correct = false
                    println("Error. Maybe your path isn`t correct.")
                }
            }
            "4" -> {
                correct = true
                try {
                    print("Enter source image path\n>> ")
                    resultImage = CyberpunkFilter.make(sc.nextLine())
                } catch (e: Exception) {
                    correct = false
                    println("Error. Maybe your path isn`t correct.")
                }
            }
            "5" -> {
                correct = true
                try {
                    print("Enter source image path\n>> ")
                    resultImage = LGBTFilter.make(sc.nextLine())
                } catch (e: Exception) {
                    correct = false
                    println("Error. Maybe your path isn`t correct.")
                }
            }
            "6" -> {
                correct = true
                try {
                    print("Enter source image path\n>> ")
                    resultImage = DefocusingFilter.make(sc.nextLine())
                } catch (e: Exception) {
                    correct = false
                    println("Error. Maybe your path isn`t correct.")
                }
            }
            "0" -> exit = true
            else -> println("Incorrect.")
        }
        // Если нет ошибки в пункте выбора, сохраняем изображение
        if (correct) {
            drawLogo()
            var str: String
            print("Enter path for saving picture\n>> ")
            while (true) {
                try {
                    str = sc.nextLine()
                    str += "\\out.png"
                    val output = File(str)
                    ImageIO.write(resultImage, "png", output)
                    break
                } catch (e: Exception) {
                    print("Oops! This is error while saving.\n" +
                            "Maybe your path isn`t correct.\n" +
                            "Enter again\n>> ")
                }
            }
        }
    }
}

private fun greeting() {
    println(" _____    _           ______                   ")
    Thread.sleep(300)
    println("|  __ \\  |_|         /  __  \\                  ")
    Thread.sleep(300)
    println("| |__| |  _    ____ |  /  \\__|   ____   _  ___ ")
    Thread.sleep(300)
    println("|  ___/  | |  / __/ | |  ____   / __ \\ | |/_  \\")
    Thread.sleep(300)
    println("| |      | | | |__  |  \\_\\   | |  ___/ |  / \\ |")
    Thread.sleep(300)
    println("|_|      |_|  \\___\\  \\______/   \\____  |__| |_|\n")
    Thread.sleep(300)
    val source = "github.com/DevVladikNT/PicGen\n"
    print("       ")
    source.forEach {
        print(it)
        Thread.sleep(40)
    }
}

private fun drawLogo() {
    val logoSrc = File(ClassLoader.getSystemResource("vk.png").file)
    val logo = ImageIO.read(logoSrc)
    for (i in resultImage.width - 247 until resultImage.width) {
        for (j in resultImage.height - 42 until resultImage.height) {
            resultImage.setRGB(i, j, logo.getRGB(i - resultImage.width + 247, j - resultImage.height + 42))
        }
    }
}