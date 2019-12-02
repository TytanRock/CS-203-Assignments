items = input("Enter a and p comma seperated: ")

ellipticalItems = items.split(',')

a = int(ellipticalItems[0].strip())
p = int(ellipticalItems[1].strip())

expression = input("Enter the two points you want to add: ")

start = expression.find('(')
mid = expression.find(',')
end = expression.find(')')
x1 = int(expression[start+1 : mid])
y1 = int(expression[mid+1 : end])

start = expression.find('(',end+1)
mid = expression.find(',',start+1)
end=expression.find(')',mid+1)
x2 = int(expression[start+1:mid])
y2 = int(expression[mid+1:end])

if x1 == x2 and y1 == y2:
    t = (3 * x1 * x1 * a) % p
    b = (2 * y1) % p
else:
    t = (y2 - y1) % p
    b = (x2 - x1) % p

if t < 0: t += p
if b < 0: b += p

b1 = pow(b, p-2, p)
s = (t * b1) % p

x3 = ((s * s) - x1 - x2) % p
y3 = (s * (x1 - x3) - y1) % p

print("The answer is: (" + str(x3) + "," + str(y3) + ")")

def pow(a, b, p):
    ret = 1
    for i in bin(b)[2:]:
        if i == '1':
            ret = (ret * ret) * a
            ret %= p
        else:
            ret = (ret * ret)
            ret %= p
    return ret
