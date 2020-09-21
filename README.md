# footballsquares

A Clojure library/program designed to manage squares for "betting" on football games.
Squares are assigned in equal numbers based on the number of players. Any 
"left over" squares are assigned to "CHARITY".

The value proposition of this code is the ability to answer the question "Who
will win if team X scores a ______?"

## Usage

>lein run home-team-name visiting-team-name players+

lein run rams saints player1 player2 player3...playerN

```
rams score touchdown
rams score failed-conversion
saints score safety
saints unscore
quit
```

## License

Copyright © 2020 Steven Katz

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
