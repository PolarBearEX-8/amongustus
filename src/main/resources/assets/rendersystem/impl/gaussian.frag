#version 120

uniform float power;
uniform int isFirstPass;

uniform sampler2D tex;
uniform sampler2D mask;
uniform vec2 direction;
uniform vec2 resolution;

float gaussian(float x, float sigma) {
    float pi = 3.14159265359;
    return exp(-(x * x) / (2.0 * sigma * sigma)) / (sqrt(2.0 * pi) * sigma);
}

void main() {
    vec2 uv = gl_FragCoord.xy / resolution;

    float maskAlpha = texture2D(mask, uv).a;
    if (maskAlpha <= 0.0) {
        if (isFirstPass == 1) {
            gl_FragColor = texture2D(tex, uv);
        } else {
            discard;
        }
        return;
    }

    vec3 color = vec3(0.0);
    float totalWeight = 0.0;

    float pixelSkip = power * 3.0;

    for (float x = -pixelSkip; x <= pixelSkip; x += 1.0) {
        float weight = gaussian(x, power);
        color += texture2D(tex, (gl_FragCoord.xy + direction * x) / resolution).rgb * weight;
        totalWeight += weight;
    }

    color /= totalWeight;

    gl_FragColor = vec4(color, 1.0);
}
