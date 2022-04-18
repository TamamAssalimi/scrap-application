package id.co.bricktest.scrapapplication.common;

public class SeleniumConstant {

    public static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/94.0.4606.54 Safari/537.36";

    public static final String BASE_URL = "https://www.tokopedia.com";
    public static final String HANDPHONE_PATH = "/p/handphone-tablet/handphone";
    public static final String PAGE = "?page=";
    public static final String TOP_ADS_URL = "https://ta.tokopedia.com/promo";

    public static final String JS_SCROLL_MEDIUM = "window.scrollBy(0,600)";

    public static final String XPATH_PRODUCT_LIST = "//div[@data-testid='lstCL2ProductList']/div";
    public static final String XPATH_PRODUCT_NAME = "//h1[@data-testid='lblPDPDetailProductName']";
    public static final String XPATH_PRODUCT_DESCRIPTION = "//*[@data-testid='lblPDPDescriptionProduk']";
    public static final String XPATH_PRODUCT_IMG_URL = "//*[@data-testid='PDPImageMain']//img";
    public static final String XPATH_PRODUCT_PRICE = "//*[@data-testid='lblPDPDetailProductPrice']";
    public static final String XPATH_PRODUCT_RATING = "//*[@data-testid='lblPDPDetailProductRatingNumber']";
    public static final String XPATH_MERCHANT_NAME = "//*[@data-testid='llbPDPFooterShopName']//h2";

    public static final String HREF = "href";
    public static final String aTAG = "a";
    public static final String SRC = "src";
    public static final String AMP = "&";
    public static final String PARAM_R = "r=";
    public static final String EMPTY = "";
    public static final String DOT = ".";
    public static final String RUPIAH_SIGN = "Rp";

}
