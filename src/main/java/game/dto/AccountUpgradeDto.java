package game.dto;

/**
 * @author ruslan.gramatic
 */
public class AccountUpgradeDto {
    Integer id;
    Integer accountId;
    Integer upgradeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Integer upgradeId) {
        this.upgradeId = upgradeId;
    }

    @Override
    public String toString() {
        return "AccountUpgradeDto{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", upgradeId=" + upgradeId +
                '}';
    }
}