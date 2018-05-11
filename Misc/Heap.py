'''
    verifica se uma arvore binaria representada como um array
    é um heap balanceado - "balanceamento" é definido por:
    1) é um heap
    2) se uma subarvore à esquerda é incompleta, não pode ter ninguém na direita
    3) a diferença nas profundidades das subárvores esquerda e direita deve ser        no máximo 1

    Autores: muita gente
'''

def left(l,i):
    if 2*i+1 < len(l): return 2*i+1
    return None

def right(l,i):
    if 2*i+2 < len(l): return 2*i+2
    return None

def éHeap(i, l, m):

    if i == None or i >= len(l) or l[i] == 0: return 1
    if l[i] > m: return 0

    pl = éHeap(left(l,i), l, l[i])
    pr = éHeap(right(l,i), l, l[i])

    if not (pr * pl): return 0 #deu pau
    if pl == 1 and abs(pr) > 1: return 0
    if abs(pl)>1 and pr == 1: pl = -1* abs(pl) 
    #convencao:
    #    deu 0 = deu pau pois violou as propriedades do heap
    #    deu + = profundidade completa
    #    deu - = profundidade incompleta

    if pl < 0:
        if abs(pl) - abs(pr) == 1: 
                    return pl-1
        else: return 0
    else:
        if abs(pl) - abs(pr) == 1 or abs(pl) - abs(pr) == 0: 
                    return pl + 1
        else: return 0

