package com.lilynlee.zhihudailynews.bean;

import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/19
 * 邮箱：1132860085@qq.com
 */

public class ZhihuDailyNews {
    /**
     * date : 20170104
     * stories : [{"images":["http://pic4.zhimg.com/53bcd79bc1c90ae798e69d01e83a43d7.jpg"],"type":0,"id":9122666,"ga_prefix":"010422","title":"小事 · 哎你不是那谁吗"},{"images":["http://pic2.zhimg.com/66af456638c88eefe2a41e3386c5a841.jpg"],"type":0,"id":9121554,"ga_prefix":"010421","title":"「卷福」为何如此迷人？腐国历经百年、用了 N 代福尔摩斯才将他炼成\u2026\u2026"},{"images":["http://pic4.zhimg.com/03c9da273fb6525c022cb4ad59614bc3.jpg"],"type":0,"id":8893755,"ga_prefix":"010420","title":"等高线、等深线、等温线、等气压线，全都来自一个意外"},{"images":["http://pic2.zhimg.com/46fc86ce438b467df68a469f4b04cd35.jpg"],"type":0,"id":9122664,"ga_prefix":"010420","title":"- 这个理财的年化利率是\u2026\u2026\r\n- 停，先说你们一年是多少天"},{"images":["http://pic2.zhimg.com/2803e10a2bbf0b6a6973de0cd63b2635.jpg"],"type":0,"id":9122463,"ga_prefix":"010418","title":"改变训练顺序，燃烧更多卡路里"},{"images":["http://pic1.zhimg.com/a06ee9626b45b48383ecd0fdc121bbbc.jpg"],"type":0,"id":9122609,"ga_prefix":"010417","title":"知乎好问题 · 有哪些好看又好玩的装置作品？"},{"images":["http://pic2.zhimg.com/270251016486c3319ce843808b730ba1.jpg"],"type":0,"id":9122110,"ga_prefix":"010416","title":"「性格」和「疾病」之间是否存在着确切的联系？"},{"images":["http://pic4.zhimg.com/0933e23f7c4e25ba5914f50ac055d6f3.jpg"],"type":0,"id":9121989,"ga_prefix":"010415","title":"衣服发型一换就认不出来，影视剧这么演不是侮辱智商吗？"},{"images":["http://pic2.zhimg.com/4a2192f8414f8900309459e54719c4d9.jpg"],"type":0,"id":9121798,"ga_prefix":"010414","title":"如何做一次有效的绩效考核面谈？"},{"images":["http://pic2.zhimg.com/c0872ea589848435e3162cad0bb938bd.jpg"],"type":0,"id":9120322,"ga_prefix":"010413","title":"神秘的 Master，让棋坛回想起被 AlphaGo 支配的恐惧"},{"title":"大误 · 拍电影捡了条狗","ga_prefix":"010412","images":["http://pic4.zhimg.com/76c4e1a3df63212556bf39e1a3bcee17.jpg"],"multipic":true,"type":0,"id":9121031},{"images":["http://pic4.zhimg.com/62385f900ad2232a14481729947f4c73.jpg"],"type":0,"id":9120282,"ga_prefix":"010411","title":"去杠杆化（率）是什么意思？"},{"title":"用大白话来捋一捋哥特式建筑的前世今生","ga_prefix":"010410","images":["http://pic1.zhimg.com/f9d6b1c292366ae99d99720d84828998.jpg"],"multipic":true,"type":0,"id":9120788},{"images":["http://pic1.zhimg.com/cccfe3fe559f65d02ae1f5c22641554c.jpg"],"type":0,"id":9117454,"ga_prefix":"010410","title":"危机与转型，1980 年代的日本经济对我们有何启示？"},{"images":["http://pic2.zhimg.com/270aa541d0b36620aed705633bfeb759.jpg"],"type":0,"id":9120292,"ga_prefix":"010408","title":"如何给孩子挑选合适的绘本？"},{"images":["http://pic3.zhimg.com/323f26997f46f07c9400806b02f1851e.jpg"],"type":0,"id":9118326,"ga_prefix":"010407","title":"一年里最适合换工作的时候，可能就是这两个月了"},{"images":["http://pic2.zhimg.com/59b08f379278144ba1c2c38273fcd211.jpg"],"type":0,"id":9119302,"ga_prefix":"010407","title":"女孩儿是如何成为「像女孩儿一样」的？"},{"images":["http://pic1.zhimg.com/46f8a19e2b4bf038936c0e9a16119c3c.jpg"],"type":0,"id":9119189,"ga_prefix":"010407","title":"AirPods 简单又好用，背后是苹果的各种黑科技"},{"images":["http://pic2.zhimg.com/11b33143e8baa482c27f38f7356510c5.jpg"],"type":0,"id":9117461,"ga_prefix":"010406","title":"瞎扯 · 如何正确地吐槽"}]
     */

    public String date;
    public List<ZhihuDailyStory> stories;

    public String getDate() {
        return date;
    }

    public List<ZhihuDailyStory> getStories() {
        return stories;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<ZhihuDailyStory> stories) {
        this.stories = stories;
    }
}
