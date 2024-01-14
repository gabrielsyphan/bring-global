CREATE TABLE IF NOT EXISTS `auth_manager` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `oauth_token` varchar(255) NOT NULL,
    `oauth_token_secret` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `oauth_token` (`oauth_token`),
    UNIQUE KEY `oauth_token_secret` (`oauth_token_secret`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;