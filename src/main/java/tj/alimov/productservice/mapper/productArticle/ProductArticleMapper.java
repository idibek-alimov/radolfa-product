package tj.alimov.productservice.mapper.productArticle;

import tj.alimov.productservice.dto.img.ImageDto;
import tj.alimov.productservice.dto.productArticle.ProductArticleDto;
import tj.alimov.productservice.dto.productArticle.ProductArticleListDto;
import tj.alimov.productservice.dto.productArticle.ProductArticleRawDto;
import tj.alimov.productservice.dto.productArticle.ProductArticleRawListDto;
import tj.alimov.productservice.exception.productPriceHistory.ProductPriceHistoryNotFoundException;
import tj.alimov.productservice.model.productArticle.ProductArticle;
import tj.alimov.productservice.model.productArticle.ProductPriceHistory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class ProductArticleMapper {

    public static ProductArticleDto toDto(ProductArticle article){
        ProductPriceHistory priceHistory = article.getProductPriceHistories().stream().filter(ProductPriceHistory::getActive).findFirst().orElseThrow(()-> new ProductPriceHistoryNotFoundException("Current product article does not have active price"));
        return new ProductArticleDto(article.getSlug(),
                                     article.getProductBaseIdentifier().toString(),
                                     formatBigDecimal(priceHistory.getPrice()),
                                     formatBigDecimal(priceHistory.getDiscountPercentage()),
                                     formatBigDecimal(calculateDiscountPrice(priceHistory.getPrice(), priceHistory.getDiscountPercentage())),
                                     article.getProduct().getName(),
                                     null
                                     );
    }

    public static ProductArticleDto rawToDto(ProductArticleRawDto raw, List<ImageDto> images){
        return new ProductArticleDto(
                raw.slug(),
                raw.productBaseIdentifier().toString(),
                formatBigDecimal(raw.currentPrice()),
                formatBigDecimal(raw.discountPercentage()),
                formatBigDecimal(calculateDiscountPrice(raw.currentPrice(), raw.discountPercentage())),
                raw.name(),
                images);
    }
    public static ProductArticleListDto listRawToListDto(ProductArticleRawListDto raw, List<ImageDto> images){
        return new ProductArticleListDto(
                raw.slug(),
                raw.productBaseIdentifier().toString(),
                formatBigDecimal(raw.currentPrice()),
                formatBigDecimal(raw.discountPercentage()),
                formatBigDecimal(calculateDiscountPrice(raw.currentPrice(), raw.discountPercentage())),
                raw.name(),
                images);
    }
    private static String formatBigDecimal(BigDecimal value){
        DecimalFormat df = new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return df.format(value);
    }
    private static BigDecimal calculateDiscountPrice(BigDecimal price, BigDecimal discountPercentage){
        if(price == null || discountPercentage == null){
            throw new IllegalArgumentException("Price and discount percentage cannot be null");
        }

        BigDecimal hundred = new BigDecimal("100");
        BigDecimal discountFactor = BigDecimal.ONE.subtract((discountPercentage.divide(hundred, 4, RoundingMode.HALF_UP)));
        return price.multiply(discountFactor).setScale(2, RoundingMode.HALF_UP);
    }


}
