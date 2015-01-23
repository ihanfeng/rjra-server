ALTER TABLE `publish_batch`
ADD COLUMN `publish_status`  tinyint(2) UNSIGNED NULL DEFAULT 0 AFTER `connection_object_id`,
ADD COLUMN `all_campaign_count`  smallint(5) UNSIGNED NULL DEFAULT 0 AFTER `publish_status`,
ADD COLUMN `all_account_count`  smallint(5) UNSIGNED NULL DEFAULT 0 AFTER `all_campaign_count`,
ADD COLUMN `all_adset_count`  smallint(5) UNSIGNED NULL DEFAULT 0 AFTER `all_account_count`,
ADD COLUMN `all_adgroup_count`  smallint(5) UNSIGNED NULL DEFAULT 0 AFTER `all_adset_count`,
ADD COLUMN `all_adcreative_count`  smallint(5) UNSIGNED NULL DEFAULT 0 AFTER `all_adgroup_count`;