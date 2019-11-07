package Helpers;

import FreeMarkerConfig.FreeMarkerConfig;
import Models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class Helper {

    public static boolean checkAnonim(User user) {

        if (user != null) {
            return false;
        }
        return true;
    }

    public static void render(HttpServletRequest request, HttpServletResponse response, String path, Map<String, Object> root) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        Configuration cfg = FreeMarkerConfig.getConfig(request);
        try {
            Template tmpl = cfg.getTemplate(path);
            try {

                boolean isAnonim = true;
                if (user != null) {
                    isAnonim = false;
                }

                root.put("isAnonim", isAnonim);
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
