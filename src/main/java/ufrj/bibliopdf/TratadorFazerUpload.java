package ufrj.bibliopdf;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("fazerUpload")
public class TratadorFazerUpload {

    @Context
    private UriInfo context;

//--------------------------------------------------------
    public TratadorFazerUpload() {
    }

//--------------------------------------------------------
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void postFazerUpload(@Context HttpServletRequest request) {
        String nomeArq = null;
        Part part = null;
        try {
            ArrayList parts = (ArrayList) request.getParts();
            
            Iterator itr = parts.iterator();
            while (itr.hasNext()) {
                part = (Part) itr.next();
                if (part.getName().compareTo("fileUpload") == 0) {
                    nomeArq = extractFileName(part);
                    System.out.println("nome do arquivo" + nomeArq);
                    String filePath = obterCaminhoPastaWeb() + nomeArq;
                    part.write(filePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//--------------------------------------------------------
    private String obterCaminhoPastaWeb(){
        Class esteServlet = getClass();
        ClassLoader cl = esteServlet.getClassLoader();
        URL url = cl.getResource("../../arquivos/");
        //String caminhoDosArquivos = url.getPath();
        String caminhoDosArquivos = "C:/debora/";
        caminhoDosArquivos = caminhoDosArquivos.replace("%20"," ");
        return caminhoDosArquivos;
    }
//--------------------------------------------------------
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
//==============================================================================
}
