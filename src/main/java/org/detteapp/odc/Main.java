package org.detteapp.odc;

import org.detteapp.odc.config.DatabaseConfig;
import org.detteapp.odc.config.ProjectConifg;
import org.detteapp.odc.entities.ArticleEntity;
import org.detteapp.odc.repositories.interfaces.ArticleRepositoryInterface;
import org.detteapp.odc.services.ArticleService;
import org.detteapp.odc.services.interfaces.ServiceInterface;
import org.detteapp.odc.views.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Collection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConifg.class);
        ArticleService articleService = context.getBean(ArticleService.class);

        Menu menu = context.getBean(Menu.class);
        menu.getMenu();

        // ArticleEntity articleEntity = new ArticleEntity();
        // articleEntity.setId(1);
        // articleEntity.setPrix(BigDecimal.valueOf(3000));
        // articleEntity.setLibelle("mango");
        // articleEntity.setSeuil(10);
        // articleEntity.setQuantity(50);

       //  articleService.save(articleEntity);
        // Collection<ArticleEntity> articles = articleService.findall();
        // articles.forEach(article -> System.out.println(article.getLibelle()));
        // ArticleEntity updatedArticle = articleService.update(articleEntity);


    }
}