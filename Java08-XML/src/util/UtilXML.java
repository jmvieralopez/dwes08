package util;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class UtilXML {

	public static Document abrirDocumentoXML(String ruta) {

		// Paso 1: inicializar el parser
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (Exception e) {
			System.out.println("Error al crear el parser XML");
			return null;
		}

		// Paso 2: procesar un archivo
		try {
			Document documento = builder.parse(new FileInputStream(ruta));
			return documento;
		} catch (Exception e) {
			System.out.println("Error al procesar el archivo XML " + ruta);
			return null;
		}

	}

	public static boolean escribirArchivoXML(Document documentoXML, String ruta) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(documentoXML);
			StreamResult result = new StreamResult(new File(ruta));
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
