use pr_itcom;

SET GLOBAL event_scheduler = 1;
#https://dev.mysql.com/doc/refman/8.0/en/create-event.html
#CREATE
#    [DEFINER = user]
#    EVENT
#    [IF NOT EXISTS]
#    event_name
#    ON SCHEDULE schedule
#    [ON COMPLETION [NOT] PRESERVE]
#    [ENABLE | DISABLE | DISABLE ON SLAVE]
#    [COMMENT 'string']
#    DO event_body;
#
#schedule:
#    AT timestamp [+ INTERVAL interval] ...
#  | EVERY interval
#    [STARTS timestamp [+ INTERVAL interval] ...]
#    [ENDS timestamp [+ INTERVAL interval] ...]
#
#interval:
#    quantity {YEAR | QUARTER | MONTH | DAY | HOUR | MINUTE |
#              WEEK | SECOND | YEAR_MONTH | DAY_HOUR | DAY_MINUTE |
#              DAY_SECOND | HOUR_MINUTE | HOUR_SECOND | MINUTE_SECOND}



DROP EVENT IF EXISTS CREAR_ALERTAS;

DELIMITER $$

CREATE EVENT CREAR_ALERTAS
ON SCHEDULE
	EVERY 1 DAY 
    STARTS CURDATE()+1
ON COMPLETION PRESERVE
DO 
	BEGIN		
		CALL ALERTA_RITMO_CARDIACO();
		CALL ALERTA_PRESENCIA();
		CALL ALERTA_PUERTA();
END $$

DELIMITER ;