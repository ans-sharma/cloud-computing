_w = [ 24,10,10,7 ]
_v = [ 24,18,18,10 ]
_W = 25

def greedy_algorithm(w, v, W):
    ordered = []
    for vi, wi in zip(v, w):
        ordered.append((vi/wi, vi, wi))
    ordered.sort(reverse=True)
    print(ordered)
    X = []
    weight = 0
    val = 0
    for _, vi, wi in ordered:
        if weight + wi <= W:
            X.append((vi, wi))
            weight += wi
            val += vi
    return X, val

def modified_greedy_algorithm(w, v, W):
    ordered = []
    for vi, wi in zip(v, w):
        ordered.append((vi/wi, vi, wi))
    ordered.sort(reverse=True)
    # print(ordered)
    S1 = []
    weight = 0
    val = 0
    for _, vi, wi in ordered:
        if weight + wi <= W:
            S1.append((vi, wi))
            weight += wi
            val += vi
        else:
            S2 = [(vi, wi)]
            val2 = vi
            if val > val2:
                return S1, val
            else:
                return S2, val2

print(greedy_algorithm(_w, _v, _W))
print("")
print(modified_greedy_algorithm(_w, _v, _W))