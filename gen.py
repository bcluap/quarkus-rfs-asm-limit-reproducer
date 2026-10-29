import sys
n=int(sys.argv[1])
f=['package org.acme;','','public class Big {']
f+=[f'    public Item item{i};' for i in range(1,n+1)]
f+=['}','']
open('src/main/java/org/acme/Big.java','w').write('\n'.join(f))
