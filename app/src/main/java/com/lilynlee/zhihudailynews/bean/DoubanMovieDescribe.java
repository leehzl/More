package com.lilynlee.zhihudailynews.bean;

import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/24
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovieDescribe {

    /**
     * rating : {"max":10,"average":7.5,"stars":"40","min":0}
     * reviews_count : 1069
     * wish_count : 28284
     * douban_site :
     * year : 2017
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2417948644.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2417948644.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2417948644.webp"}
     * alt : https://movie.douban.com/subject/25900945/
     * id : 25900945
     * mobile_url : https://movie.douban.com/subject/25900945/mobile
     * title : 美女与野兽
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/25900945
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/25900945/cinema/
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["爱情","奇幻","歌舞"]
     * collect_count : 74396
     * casts : [{"alt":"https://movie.douban.com/celebrity/1053624/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/10321.jpg","large":"https://img3.doubanio.com/img/celebrity/large/10321.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/10321.jpg"},"name":"艾玛·沃森","id":"1053624"},{"alt":"https://movie.douban.com/celebrity/1027527/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1359042267.71.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1359042267.71.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1359042267.71.jpg"},"name":"丹·史蒂文斯","id":"1027527"},{"alt":"https://movie.douban.com/celebrity/1276136/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/36425.jpg","large":"https://img3.doubanio.com/img/celebrity/large/36425.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/36425.jpg"},"name":"卢克·伊万斯","id":"1276136"},{"alt":"https://movie.douban.com/celebrity/1040531/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/40528.jpg","large":"https://img1.doubanio.com/img/celebrity/large/40528.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/40528.jpg"},"name":"凯文·克莱恩","id":"1040531"}]
     * current_season : null
     * original_title : Beauty and the Beast
     * summary : 《美女与野兽》根据迪士尼1991年经典动画片及闻名全球的经典童话改编，讲述了少女贝儿的奇幻旅程——为了解救触怒野兽的父亲，勇敢善良的她只身一人来到古堡，代替父亲被囚禁其中。贝儿克服了恐惧，和城堡里的魔法家具们成为了朋友，也渐渐发现野兽其实是受了诅咒的王子，他可怖的外表下藏着一颗善良温柔的内心；这个故事也带领观众明白——美不仅仅是外表，更重要的是内心。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1027245/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/42170.jpg","large":"https://img3.doubanio.com/img/celebrity/large/42170.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/42170.jpg"},"name":"比尔·康顿","id":"1027245"}]
     * comments_count : 38694
     * ratings_count : 70407
     * aka : []
     */

    public RatingBean rating;
    public int reviews_count;
    public int wish_count;
    public String douban_site;
    public String year;
    public ImagesBean images;
    public String alt;
    public String id;
    public String mobile_url;
    public String title;
    public Object do_count;
    public String share_url;
    public Object seasons_count;
    public String schedule_url;
    public Object episodes_count;
    public int collect_count;
    public Object current_season;
    public String original_title;
    public String summary;
    public String subtype;
    public int comments_count;
    public int ratings_count;
    public List<String> countries;
    public List<String> genres;
    public List<CastsBean> casts;
    public List<DirectorsBean> directors;
    public List<?> aka;

    public RatingBean getRating() {
        return rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public String getYear() {
        return year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public String getAlt() {
        return alt;
    }

    public String getId() {
        return id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getSummary() {
        return summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public List<?> getAka() {
        return aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 7.5
         * stars : 40
         * min : 0
         */

        public int max;
        public double average;
        public String stars;
        public int min;

        public int getMax() {
            return max;
        }

        public double getAverage() {
            return average;
        }

        public String getStars() {
            return stars;
        }

        public int getMin() {
            return min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2417948644.webp
         * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2417948644.webp
         * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2417948644.webp
         */

        public String small;
        public String large;
        public String medium;

        public String getSmall() {
            return small;
        }

        public String getLarge() {
            return large;
        }

        public String getMedium() {
            return medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1053624/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/10321.jpg","large":"https://img3.doubanio.com/img/celebrity/large/10321.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/10321.jpg"}
         * name : 艾玛·沃森
         * id : 1053624
         */

        public String alt;
        public AvatarsBean avatars;
        public String name;
        public String id;

        public String getAlt() {
            return alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/10321.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/10321.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/10321.jpg
             */

            public String small;
            public String large;
            public String medium;

            public String getSmall() {
                return small;
            }

            public String getLarge() {
                return large;
            }

            public String getMedium() {
                return medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1027245/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/42170.jpg","large":"https://img3.doubanio.com/img/celebrity/large/42170.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/42170.jpg"}
         * name : 比尔·康顿
         * id : 1027245
         */

        public String alt;
        public AvatarsBeanX avatars;
        public String name;
        public String id;

        public String getAlt() {
            return alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/42170.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/42170.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/42170.jpg
             */

            public String small;
            public String large;
            public String medium;

            public String getSmall() {
                return small;
            }

            public String getLarge() {
                return large;
            }

            public String getMedium() {
                return medium;
            }
        }
    }
}
