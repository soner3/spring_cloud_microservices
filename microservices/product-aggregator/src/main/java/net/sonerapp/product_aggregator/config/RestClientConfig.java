package net.sonerapp.product_aggregator.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import net.sonerapp.product_aggregator.service.client.ProductService;
import net.sonerapp.product_aggregator.service.client.RecommendationService;
import net.sonerapp.product_aggregator.service.client.ReviewService;

@Configuration
public class RestClientConfig {

        @Bean
        @LoadBalanced
        RestClient.Builder restClientBuilder() {
                return RestClient.builder();
        }

        @Bean
        ProductService productService(@LoadBalanced RestClient.Builder restClientBuilder) {
                RestClient restClient = restClientBuilder
                                .baseUrl("lb://PRODUCT")
                                .build();
                RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
                return HttpServiceProxyFactory
                                .builderFor(restClientAdapter)
                                .build()
                                .createClient(ProductService.class);
        }

        @Bean
        RecommendationService recommendationService(@LoadBalanced RestClient.Builder restClientBuilder) {
                RestClient restClient = restClientBuilder
                                .baseUrl("lb://RECOMMENDATION")
                                .build();
                RestClientAdapter adapter = RestClientAdapter.create(restClient);
                return HttpServiceProxyFactory
                                .builderFor(adapter)
                                .build()
                                .createClient(RecommendationService.class);
        }

        @Bean
        ReviewService reviewService(@LoadBalanced RestClient.Builder restClientBuilder) {
                RestClient restClient = restClientBuilder
                                .baseUrl("lb://REVIEW")
                                .build();
                RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
                return HttpServiceProxyFactory
                                .builderFor(restClientAdapter)
                                .build()
                                .createClient(ReviewService.class);
        }

}
