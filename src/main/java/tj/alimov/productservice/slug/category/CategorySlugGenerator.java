package tj.alimov.productservice.slug.category;

public class CategorySlugGenerator {
    public static String generateSlug(String input){
        if(input == null) return "";
        String slug = input.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        return slug;
    }
}
