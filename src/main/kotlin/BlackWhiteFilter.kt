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
        blur()
        render()
        blur()
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

    private fun blur() {
        var col1: Color
        var col2: Color
        var col3: Color
        var col4: Color
        var col5: Color
        var col6: Color
        var col7: Color
        var col8: Color
        var col9: Color
        var col10: Color
        var col11: Color
        var col12: Color
        var curColor: Color
        repeat(50) {
            for (i in 2 until result.width - 2) {
                for (j in 2 until result.height - 2) {
                    col1 = Color(result.getRGB(i - 1, j))
                    col2 = Color(result.getRGB(i, j - 1))
                    col3 = Color(result.getRGB(i + 1, j))
                    col4 = Color(result.getRGB(i, j + 1))
                    col5 = Color(result.getRGB(i + 1, j + 1))
                    col6 = Color(result.getRGB(i - 1, j + 1))
                    col7 = Color(result.getRGB(i + 1, j - 1))
                    col8 = Color(result.getRGB(i - 1, j - 1))
                    col9 = Color(result.getRGB(i, j + 2))
                    col10 = Color(result.getRGB(i + 2, j))
                    col11 = Color(result.getRGB(i, j - 2))
                    col12 = Color(result.getRGB(i - 2, j))
                    curColor = Color(
                        (col1.red + col2.red + col3.red + col4.red + col5.red + col6.red + col7.red + col8.red + col9.red + col10.red + col11.red + col12.red) / 12,
                        (col1.green + col2.green + col3.green + col4.green + col5.green + col6.green + col7.green + col8.green + col9.green + col10.green + col11.green + col12.green) / 12,
                        (col1.blue + col2.blue + col3.blue + col4.blue + col5.blue + col6.blue + col7.blue + col8.blue + col9.blue + col10.blue + col11.blue + col12.blue) / 12
                    )
                    result.setRGB(i, j, curColor.rgb)
                }
            }
        }
    }

}