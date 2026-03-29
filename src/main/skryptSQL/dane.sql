-- USERS
INSERT INTO users (first_name, last_name, birthday, email) VALUES
                                                               ( 'Jan', 'Podeszko', '1990-05-10', 'jan.Podeszko@email.com'),
                                                               ( 'Anna', 'Podeszko', '1995-08-22', 'anna.Podeszko@email.com');

SELECT * FROM users

-- TRAININGS
    INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed) VALUES
    (1, '2026-03-20 07:00:00', '2026-03-20 08:00:00', 'RUNNING', 10.5, 10.5),
    (2, '2026-03-21 06:30:00', '2026-03-21 08:00:00', 'CYCLING', 35.0, 23.3);

SELECT * FROM trainings

-- HEALTH_METRICS
    INSERT INTO health_metrics (user_id, date, weight, height, heart_rate) VALUES
    (1, '2027-03-20', 75.0, 180, 70),
    ( 2, '2029-03-21', 60.0, 165, 68);

SELECT * FROM health_metrics

SELECT
    hm.id,
    hm.user_id,
    u.last_name,
    hm.date,
    hm.weight,
    hm.height,
    hm.heart_rate
FROM health_metrics hm
         JOIN users u ON hm.user_id = u.id;
-- STATISTICS
INSERT INTO statistics (user_id, total_trainings, total_distance, total_calories_burned) VALUES
                                                                                             (1, 5, 52.5, 3210),
                                                                                             (2, 8, 210.0, 5200);


SELECT * FROM statistics

SELECT * FROM activity_type

