# edts-ticketing

My implementation for this concert ticket require 4 tables which consist of:
1. User
  - this table store user data with unique email.
2. Concert
  - this table store list of concert that is available on our system.
  - ticket_amount indicates how many seats available for this concert
3. Booking
  - one concert can have multiple booking, but the total ticket_amount for one concert-booking must not exceeded ticket_amount in concert field
4. User Booking
  - to store user booking data, user_id and booking_id combination is a unique value. same user cannot have same booking_id.
  - one user cannot buy ticket_amount more than ticket_amount in booking table.

