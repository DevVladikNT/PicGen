import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object ColorShiftsFilter {

    private lateinit var image: BufferedImage
    private lateinit var result: BufferedImage

    fun make(path: String): BufferedImage {
        val file = File(path)
        image = ImageIO.read(file)
        result = image.getSubimage(0, 0, image.width, image.height)
        render()
        return result
    }

    private fun render() {
        println("Wait please.")
        repeat(20) {
            for (i in 1 until image.width) {
                for (j in 1 until image.height) {
                    result.setRGB(
                        i, j, Color(
                            (Color(result.getRGB(i, j)).red * 0.5 + Color(result.getRGB(i - 1, j)).red * 0.5).toInt(),
                            (Color(result.getRGB(i, j)).green * 0.5 + Color(result.getRGB(i, j - 1)).green * 0.5).toInt(),
                            (Color(result.getRGB(i, j)).blue * 0.5 + Color(result.getRGB(i - 1, j - 1)).blue * 0.5).toInt()
                        ).rgb
                    )
                }
            }
        }
    }

}