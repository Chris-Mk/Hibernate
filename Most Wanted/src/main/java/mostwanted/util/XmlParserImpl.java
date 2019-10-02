package mostwanted.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

public class XmlParserImpl implements XmlParser {

    @Override
    @SuppressWarnings("unchecked")
    public <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException {
        return (O) JAXBContext.newInstance(objectClass)
                .createUnmarshaller()
                .unmarshal(new StringReader(filePath));
    }
}
