# Analysis of Canoe Trip Problem

### Description of Algorithm
This algorithm works from the last layer to the first layer.

It finds the cheapest path from the post corresponding to its layer to the final post.

It does this by comparing all the costs available to it to the costs available at other layers, finding the minimum of them all.

PseudoCode below:
```
Matrix m[n,n] //Trimmed off first column and last row so [0,0] and [n-1,n-1] is valid
Post p[n]

p[n-1].value = m[n-1,n-1] 
For i = n - 2 to 0
  p[i].value = min { m[i,0] + p[i+1].value, m[i,1] + p[i+2].value, ..., m[i,n-1] }

return p[0].value
```

Formula:
A(l,M) = min {A(l+1, M) + M\[l,0\], A(l+2, M) + M\[l, 1\], ..., M\[l, n-1\] }

### Analysis of time efficiency
1 -> 1

2 -> 1 + 2 = 3

3 -> 1 + 2 + 3 = 6

T(n) = n + T(n-1): T(0) = 0

T(n) = n(n+1)/2

O(T(n)) = O(n^2)

---

This makes sense because n is the number of rows and columns in an nxn matrix.

Unless we can skip values, this means the fastest algorithm possible is an n^2 algorithm.