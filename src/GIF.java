import java.util.List;

public class GIF {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public class Data{
        private Image images;

        public Image getImages() {
            return images;
        }
    }

    public class Image{
        private Original original;

        public Original getOriginal() {
            return original;
        }
    }

    public class Original{
        private String url;

        public String getUrl() {
            return url;
        }
    }
}
