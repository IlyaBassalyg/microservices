package com.learn.ib.resource_service.service.parser;

import com.learn.ib.resource_service.exception.InvalidBodyException;
import com.learn.ib.resource_service.model.dto.SongMetadataDto;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MP3Parser {
    private final BodyContentHandler handler;
    private final Metadata metadata;
    private final ParseContext pcontext;
    private final byte[] resourceBody;

    public MP3Parser(BodyContentHandler handler, Metadata metadata, ParseContext pcontext, byte[] resourceBody) {
        this.handler = handler;
        this.metadata = metadata;
        this.pcontext = pcontext;
        this.resourceBody = resourceBody;
    }

    public SongMetadataDto parseBody(int resourceId) {

        try (InputStream inputstream = new ByteArrayInputStream(resourceBody)) {
            Tika tika = new Tika();
            if (!tika.detect(inputstream).equals("audio/mpeg")) {
                throw new TikaException("");
            }

            Mp3Parser mp3Parser = new Mp3Parser();
            mp3Parser.parse(inputstream, handler, metadata, pcontext);

            return new SongMetadataDto(resourceId,
                    metadata.get("dc:title"),
                    metadata.get("xmpDM:artist"),
                    metadata.get("xmpDM:album"),
                    formatSecondsToMmSs((int) Math.round(Double.parseDouble(metadata.get("xmpDM:duration")))),
                    metadata.get("xmpDM:releaseDate"));
        } catch (TikaException | IOException | SAXException e) {
            throw new InvalidBodyException("The request body is invalid MP3");
        }
    }

    public static String formatSecondsToMmSs(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        String formattedMinutes = String.format("%02d", minutes);
        String formattedSeconds = String.format("%02d", seconds);

        return formattedMinutes + ":" + formattedSeconds;
    }
}
