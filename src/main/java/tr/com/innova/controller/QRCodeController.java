package tr.com.innova.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mehmet on 21.02.2017.
 */
@Controller
@Slf4j
public class QRCodeController {

    @RequestMapping("/generate")
    public String generateQRPage() {
        return "generate";
    }

    @RequestMapping(value = "/qrcode", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody  byte[] getQR() throws IOException {
        BitMatrix bitMatrix = null;

        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();

            hints.put(EncodeHintType.QR_VERSION, 4);


            bitMatrix = new MultiFormatWriter().encode("111111-222222-333333-444444-555555-666666", BarcodeFormat.QR_CODE, 256, 256, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", bos);

        return bos.toByteArray();
    }

}
