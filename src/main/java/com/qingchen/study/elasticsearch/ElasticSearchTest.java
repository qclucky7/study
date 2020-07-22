package com.qingchen.study.elasticsearch;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ElasticSearchTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-16 16:40
 **/
@RestController
@RequestMapping("/es")
public class ElasticSearchTest {

//    @Autowired(required = false)
//    private ElasticsearchTemplate restTemplate;
//
//    @Autowired(required = false)
//    private ArticleRepository articleRepository;
//
//    @Autowired(required = false)
//    private GoodsRepository goodsRepository;
//
//    @GetMapping("/el")
//    public void myTest(){
//
//        restTemplate.createIndex(Article.class);
//
//    }
//
//    @GetMapping("/add")
//    public void myTestData(){
//
//        List<Goods> foods = Arrays.asList(
//                new Goods(1L, "哇哈哈", "哇哈哈饮料", "零食喝的", "drink"),
//                new Goods(2L, "可口可乐", "可乐饮料", "零食喝的","drink"),
//                new Goods(3L, "百事可乐", "百事可乐", "零食喝的","drink"),
//                new Goods(4L, "百岁山", "矿泉水", "零食喝的","drink"),
//                new Goods(5L, "怡宝", "矿泉水", "零食喝的","drink"),
//                new Goods(6L, "小当家", "方便面", "零食吃的","eat"),
//                new Goods(7L, "牛头牌牛肉干", "牛肉干", "零食吃的","eat"),
//                new Goods(8L, "乐事薯片", "薯片", "零食膨化吃的","eat")
//                );
//
//        List<Goods> uses = Arrays.asList(
//                new Goods(9L, "卫生纸", "纸张", "日用品一次性","daily"),
//                new Goods(10L, "纯品抽纸", "抽纸", "日用品一次性","daily"),
//                new Goods(11L, "黑人牙膏", "牙膏", "日用品","daily"),
//                new Goods(12L, "无敌牙膏", "牙膏", "日用品","daily"),
//                new Goods(13L, "清扬洗发露", "洗发露", "日用品洗发","daily"),
//                new Goods(14L, "清扬护发露", "护发露", "日用品护法","daily")
//                );
//
//        List<Goods> products = Arrays.asList(
//                new Goods(15L, "苹果手机", "手机", "电子产品手机", "phone"),
//                new Goods(16L, "华为手机", "手机", "电子产品手机","phone"),
//                new Goods(17L, "苹果电脑", "电脑", "电子产品电脑","computer"),
//                new Goods(18L, "华为电脑", "电脑", "电子产品电脑","computer"),
//                new Goods(19L, "苹果手表", "手表", "电子产品手表","watch"),
//                new Goods(20L, "华为手表", "手表", "电子产品手表","watch"),
//                new Goods(21L, "小米手表", "手表", "电子产品手表","watch"),
//                new Goods(21L, "小米手机", "手机", "电子产品手机","phone")
//                );
//
//
//       goodsRepository.saveAll(foods);
//       goodsRepository.saveAll(uses);
//       goodsRepository.saveAll(products);
//
//    }
//
//    @PostMapping("/search")
//    public Page<Goods> search(
//            @RequestParam String goodName,
//            @RequestParam String goodContext,
//            @RequestParam String keyWorld,
//            @RequestParam Integer sort,
//            @RequestParam String type
//            ){
//
//        BoolQueryBuilder builder = QueryBuilders.boolQuery();
//        if (StringUtils.isNotEmpty(goodName)){
//            builder.must(QueryBuilders.termQuery("goodName", goodName));
//        }
//        if (StringUtils.isNotEmpty(goodContext)){
//            builder.must(QueryBuilders.termQuery("goodContext", goodContext));
//        }
//        if (StringUtils.isNotEmpty(keyWorld)){
//            builder.must(QueryBuilders.queryStringQuery(keyWorld));
//        }
//        if (StringUtils.isNotEmpty(type)){
//            builder.must(QueryBuilders.termQuery("type", type));
//        }
//
//        FieldSortBuilder fieldSortBuilder = null;
//        if (sort == 1){
//            fieldSortBuilder = SortBuilders.fieldSort("id").order(SortOrder.ASC);
//        } else if (sort == 2){
//            fieldSortBuilder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
//        }
//
//
//        //2.构建查询
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        //将搜索条件设置到构建中
//        nativeSearchQueryBuilder.withQuery(builder);
//        //nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("type"));
//        //将分页设置到构建中
//        nativeSearchQueryBuilder.withPageable(PageRequest.of(0, 20));
//        //将排序设置到构建中
//        nativeSearchQueryBuilder.withSort(fieldSortBuilder);
//        //生产NativeSearchQuery
//        NativeSearchQuery query = nativeSearchQueryBuilder.build();
//
//        System.out.println(query.toString());
//
//        Page<Goods> search = goodsRepository.search(query);
//
//        return search;
//
//    }
//
//    @GetMapping("/ell")
//    public void myTest1(){
//
//        System.out.println(articleRepository);
//
//            Article article = new Article();
//            article.setId(52L);
//            article.setTitle("美丽" );
//            article.setContext("5月14日，中共中央政治局常务委员会召开会议，分析国内外新冠肺炎疫情防控形势，研究部署抓好常态化疫情防控措施落地见效，研究提升产业链供应链稳定性和竞争力。中共中央总书记习近平主持会议并发表重要讲话。");
//            articleRepository.save(article);
//
//
//    }
//
//    public void AggregationAndCount(Class clazz) {
//        TermsAggregationBuilder builder = AggregationBuilders.terms("tag_count").field("tag")
//                .subAggregation(AggregationBuilders.terms("type_count").field("type"));
//        Document document = (Document) clazz.getAnnotation(Document.class);
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withIndices(document.indexName())
//                .withTypes(document.type())
//                .withFields()
//                .addAggregation(builder)
//                .build();
//        Aggregations aggregation = restTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
//            @Override
//            public Aggregations extract(SearchResponse searchResponse) {
//                return searchResponse.getAggregations();
//            }
//        });
//        StringTerms teamAgg = (StringTerms) aggregation.asMap().get("tag_count");
//        List<StringTerms.Bucket> bucketList = teamAgg.getBuckets();
//        for (StringTerms.Bucket bucket : bucketList) {
//            LongTerms longTerms=(LongTerms)bucket.getAggregations().asMap().get("type_count");
//            List<LongTerms.Bucket> bucketList1 = longTerms.getBuckets();
//            for (LongTerms.Bucket bucket1 : bucketList1){
//                String key = bucket1.getKeyAsString();
//                long num = bucket1.getDocCount();
//                System.out.println(key);
//                System.out.println(num);
//            }
//        }
//    }
//
//
//
//    @GetMapping("/simpleQuery")
//    public List<Article> myTest2(){
//
//        //articleRepository.findById()
//        //articleRepository.findAllById()
//
//
//        List<Article> objects = new ArrayList<>();
//        Iterable<Article> all = articleRepository.findAll();
//        Iterator<Article> iterator = all.iterator();
//        while (iterator.hasNext()){
//            objects.add(iterator.next());
//        }
//        System.out.println(objects.toString());
//        return objects;
//    }
//
//    @GetMapping("/SimpleQuery/{title}")
//    public List<Article> myTest3(@PathVariable String title){
//
//        //PageRequest.of()
//
//        //articleRepository.findById()
//        //articleRepository.findAllById()
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//
//        return articleRepository.findArticleByTitle(title);
//    }
//
//
//    @GetMapping("/nativeQuery/{title}")
//    public List<Article> myTest4(@PathVariable String title){
//
//        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.queryStringQuery(title).defaultField("title"))
//                .withPageable(PageRequest.of(0, 20))
//                .build();
//        return restTemplate.queryForList(nativeSearchQuery, Article.class);
//    }



}
