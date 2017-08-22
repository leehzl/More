package com.lilynlee.zhihudailynews.bean;

import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/19
 * 邮箱：1132860085@qq.com
 */

public class ZhihuDailyLongComments {

    public List<CommentsBean> comments;

    public static class CommentsBean {
        /**
         * author : 飞翔吧猪小胖
         * content : 另外提醒你，你自己的逻辑也说了穷人家孩子想求稳定，既然想求稳定就别老想着马云、王健林！也别想着这个月房贷不够钱下个月娃的奶粉要买进口！人家是做企业凭本事赚钱，你一公务员要老想着赚钱那是啥心理吃土的群众都明白！
         * avatar : http://pic3.zhimg.com/0d7316aae644b6dc3d0050b287e9895a_im.jpg
         * time : 1489916694
         * reply_to : {"content":"第一不好意思公务员的工资没你说的那么低，或许不够你泡吧潇洒但是养家糊口还是没问题的。第二你大学毕业出来混社会了吗？有才华就一定升职加薪！我又不是没在私企待过。第三按你们说的如果公务员都是富二代，因为富二代不在乎钱，不会权力变现，你了解人性吗？就问大家一句话谁会嫌钱多。第四穷人就不应该考公务员！在马云王健林眼里有几个有钱人，是不是在资格限制中应加一条资产低于多少不得参加考试？","status":0,"id":28446301,"author":"likai磊"}
         * id : 28447611
         * likes : 1
         */

        public String author;
        public String content;
        public String avatar;
        public int time;
        public ReplyToBean reply_to;
        public int id;
        public int likes;

        public static class ReplyToBean {
            /**
             * content : 第一不好意思公务员的工资没你说的那么低，或许不够你泡吧潇洒但是养家糊口还是没问题的。第二你大学毕业出来混社会了吗？有才华就一定升职加薪！我又不是没在私企待过。第三按你们说的如果公务员都是富二代，因为富二代不在乎钱，不会权力变现，你了解人性吗？就问大家一句话谁会嫌钱多。第四穷人就不应该考公务员！在马云王健林眼里有几个有钱人，是不是在资格限制中应加一条资产低于多少不得参加考试？
             * status : 0
             * id : 28446301
             * author : likai磊
             */

            public String content;
            public int status;
            public int id;
            public String author;
        }
    }
}
