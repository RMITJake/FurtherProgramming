SELECT * FROM bookings AS b
INNER JOIN venues AS v ON b.venueId = v.id
INNER JOIN events AS e ON b.eventId = e.id;