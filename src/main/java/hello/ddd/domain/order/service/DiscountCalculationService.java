package hello.ddd.domain.order.service;

import hello.ddd.domain.coupon.model.Coupon;
import hello.ddd.domain.member.policy.MemberGrade;
import hello.ddd.domain.order.vo.Money;
import hello.ddd.domain.order.model.OrderLine;
import java.util.List;

//여러 애그리거트가 필요한 계산 로직이나 한 애그리거트에 널기에는 다소 복잡한 로직 도메인 서비스로 구현
public class DiscountCalculationService {

    public Money calculatediscountAmounts(
            List<OrderLine> orderLines,
            List<Coupon> coupons,
            MemberGrade memberGrade
    ) {
       Money couponDiscount = coupons.stream()
               .map(coupon -> calculateDiscount(coupon))
               .reduce(new Money(0), (v1, v2) -> v1.add(v2));

       Money membershipDiscount = calculateDiscount(orderer.getMember().getGrade);

        return couponDiscount.add(membershipDiscount);
    }

    private Money calculateDiscount(Coupon coupon) {
        return new Money(0);
    }

    private Money calculateDiscount(MemberGrade grade) {
        return new Money(0);
    }
}
