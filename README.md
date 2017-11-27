<h1>The Mahjong Project</h1>

<h2>package main</h2>
	<p><i>Under construction...</i></p>

<h2>package player</h2>
	<p><i>Under construction...</i></p>

<h2>package tile</h2>
	<p><i>Under construction...</i></p>
	<h3>Tile</h3>
	<table>
		<tr>
			<th>Constructor</th>
			<th>Description</th>
		</tr>
		<tr>
			<td>Tile(int rank, Suit suit)</td>
			<td>
				<p>Constructs and initializes a ranked tile</p>
				<p>
					Throws illegal argument exception<br>
					if rank is outside the range 1 to 9<br>
					or if suit is nonranked
				</p>
			</td>
		</tr>
		<tr>
			<td>Tile(Suit suit)</td>
			<td>
				<p>Constructs and initializes a nonranked tile</p>
				<p>Throws illegal argument exception<br>if suit is ranked</p>
			</td>
		</tr>
	</table>