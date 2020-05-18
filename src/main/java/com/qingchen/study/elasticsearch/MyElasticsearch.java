package com.qingchen.study.elasticsearch;

import com.qingchen.study.elasticsearch.entity.Article;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.index.query.functionscore.WeightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.SourceSimpleFragmentsBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * @ClassName MyElasticsearch
 * @description:
 * @author: WangChen
 * @create: 2020-05-16 13:38
 **/
public class MyElasticsearch {
    /**
     * elasticSearch和数据库对比
     * <p>
     * 索引index   =  数据库
     * 类型type   =   数据库表 table
     * 文档document =  数据库行数据 row
     * 字段field  =  数据库列
     * 映射mapping = 约束
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    public void query() {
        //http://localhost:9200/_analyze?pretty=true 查看默认分词器
        //{
        //	"analyzer":"standard",
        //	"text": "drink"
        //}
        //1.创建QueryBuilder(即设置查询条件)这儿创建的是组合查询(也叫多条件查询),后面会介绍更多的查询方法
        /*组合查询BoolQueryBuilder
         * must(QueryBuilders)   :AND
         * mustNot(QueryBuilders):NOT
         * should:               :OR
         */
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        //builder下有must、should以及mustNot 相当于sql中的and、or以及not
        //设置模糊搜索,博客的简诉中有学习两个字
        builder.must(QueryBuilders.fuzzyQuery("sumary", "学习"));
        //设置要查询博客的标题中含有关键字
        builder.must(new QueryStringQueryBuilder("man").field("springdemo"));
        //按照博客的评论数的排序是依次降低
        FieldSortBuilder sort = SortBuilders.fieldSort("commentSize").order(SortOrder.DESC);
        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page = PageRequest.of(0, 10);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();


        //3.执行方法1
        Page<Article> page1 = articleRepository.search(query);
        //执行方法2：注意，这儿执行的时候还有个方法那就是使用elasticsearchTemplate
        List<Article> blogList = elasticsearchTemplate.queryForList(query, Article.class);
        //4.获取总条数(用于前端分页)

        int total = (int) page1.getTotalElements();

    }

    public void queryBuilder() {

        //不分词查询 参数1： 字段名，参数2：字段查询值，因为不分词，所以汉字只能查询一个字，英语是一个单词.
        QueryBuilder queryBuilder = QueryBuilders.termQuery("fieldName", "fieldlValue");
        //分词查询，采用默认的分词器
        QueryBuilder queryBuilder2 = QueryBuilders.matchQuery("fieldName", "fieldlValue");

        //不分词查询，参数1： 字段名，参数2：多个字段查询值,因为不分词，所以汉字只能查询一个字，英语是一个单词.
        QueryBuilder queryBuilder1 = QueryBuilders.termsQuery("fieldName", "fieldlValue1", "fieldlValue2...");
        //分词查询，采用默认的分词器
        QueryBuilder queryBuilder4 = QueryBuilders.multiMatchQuery("fieldlValue", "fieldName1", "fieldName2", "fieldName3");
        //匹配所有文件，相当于就没有设置查询条件
        QueryBuilder queryBuilder3 = QueryBuilders.matchAllQuery();

        QueryBuilders.functionScoreQuery(QueryBuilders.queryStringQuery(""));

        WeightBuilder weightBuilder = ScoreFunctionBuilders.weightFactorFunction(2);

        //模糊查询常见的5个方法如下
        //1.常用的字符串查询
        QueryBuilders.queryStringQuery("fieldValue").field("fieldName");//左右模糊
        //2.常用的用于推荐相似内容的查询
        //如果不指定filedName，则默认全部，常用在相似内容的推荐上
        QueryBuilders.moreLikeThisQuery(new String[] {"fieldName"}, new String[]{"java"},null);

        //3.前缀查询  如果字段没分词，就匹配整个字段前缀
        QueryBuilders.prefixQuery("fieldName","fieldValue");
        //4.fuzzy query:分词模糊查询，通过增加fuzziness模糊属性来查询,如能够匹配hotelName为tel前或后加一个字母的文档，fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询
        QueryBuilders.fuzzyQuery("hotelName", "tel").fuzziness(Fuzziness.ONE);
        //5.wildcard query:通配符查询，支持* 任意字符串；？任意一个字符
        QueryBuilders.wildcardQuery("fieldName","ctr*");//前面是fieldname，后面是带匹配字符的字符串
        QueryBuilders.wildcardQuery("fieldName","c?r?");

    }

}
