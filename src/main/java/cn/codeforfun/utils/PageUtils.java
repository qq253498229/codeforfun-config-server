package cn.codeforfun.utils;

import com.github.pagehelper.IPage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {
    public static Object from(Pageable pageable) {
        return new IPage() {
            @Override
            public Integer getPageNum() {
                return pageable.getPageNumber();
            }

            @Override
            public Integer getPageSize() {
                return pageable.getPageSize();
            }

            @Override
            public String getOrderBy() {
                return PageUtils.getOrderBy(pageable);
            }
        };
    }

    public static String getOrderBy(Pageable pageable) {
        Sort sort = pageable.getSort();
        if (sort.isUnsorted()) {
            return null;
        }
        StringBuilder orderBy = new StringBuilder();
        for (Sort.Order order : sort.toList()) {
            orderBy.append(order.getProperty()).append(" ").append(order.getDirection().toString()).append(",");
        }
        orderBy = new StringBuilder(orderBy.substring(0, orderBy.length() - 1));
        return orderBy.toString();
    }
}
