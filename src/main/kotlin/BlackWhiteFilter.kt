import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

object BlackWhiteFilter {

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
        var value = 0
        for (i in 0 until image.width) {
            for (j in 0 until image.height) {
                value = (Color(image.getRGB(i, j)).red * 0.299 + Color(image.getRGB(i, j)).green * 0.587 + Color(image.getRGB(i, j)).blue * 0.114).toInt()
                result.setRGB(i, j, Color(value, value, value).rgb)
            }
        }
    }

}