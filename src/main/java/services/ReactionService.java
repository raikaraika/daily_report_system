package services;

import java.time.LocalDateTime;

import actions.views.ReactionConverter;
import actions.views.ReactionView;

public class ReactionService extends ServiceBase {

	/**
	 * いいねするリンクを押下でデータを1件作成し、いいねテーブルに登録
	 * @param rav 日報の登録内容
	 */
	public void create(ReactionView rav){
			LocalDateTime ldt = LocalDateTime.now();
			rav.setCreatedAt(ldt);
			rav.setUpdatedAt(ldt);
			createInternal(rav);
		}

	/**
     * いいねデータを1件登録する
     * @param rav いいねデータ
     */
    private void createInternal(ReactionView rav) {

        em.getTransaction().begin();
        em.persist(ReactionConverter.toModel(rav));
        em.getTransaction().commit();

    }

}
